package com.clover.form.controller;

import com.clover.form.model.DocumentDetails;
import com.clover.form.model.EmpUser;
import com.clover.form.model.Employee;
import com.clover.form.model.ResponseMsg;
import com.clover.form.service.DocumentUploadService;
import com.clover.form.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@SuppressWarnings("unused")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DocumentUploadService documentUploadService;

	@GetMapping("/emp")
	public List<Employee> viewPage(Model model) {
		System.out.println("api hitted for getAllEmp");
		return employeeService.getAllEmployees();
	}

	@PostMapping(value = "/emp/add_new",
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public void saveNewEmployeeForm(@RequestPart Employee employee,
									@Nullable @RequestPart MultipartFile file) {

		System.out.println("api hitted for addNewEmp");
		System.out.println(employee+" "+file);
		try {
			if(file != null){
				employee.setAvatar(file.getBytes());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		employee.setPanNumber(employee.getPanNumber().toUpperCase());
		employeeService.saveEmployee(employee);
	}

	@PutMapping("/emp/edit/{id}")
	public void updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
		System.out.println("api hitted for editById");
		employee.setPanNumber(employee.getPanNumber().toUpperCase());
		employee.setId(id);
		employeeService.updateEmployee(employee);
	}

	@GetMapping("/emp/show_by_id/{id}")
	public Employee showFormForUpdate(@PathVariable ("id") long id) {
		System.out.println("api hitted for getById");
		return employeeService.getEmployeeById(id);
	}

	@DeleteMapping("/emp/delete/{id}")
	public void deleteEmployee(@PathVariable (value = "id") long id) {
		System.out.println("api hitted for delete");
		this.employeeService.deleteEmployeeById(id);
	}

	@PostMapping(value = "/emp/upload/{id}/avatar")
	public void uploadAvatar(@PathVariable (value = "id") long id,
							 @RequestParam MultipartFile file){
		System.out.println("hitted for upload avatar"+file.toString());
		employeeService.setProfilePicture(id,file);
	}

	@GetMapping("/emp/show_by_id/{id}/avatar")
	public byte[] getAvatar( @PathVariable ("id") long id ){
		System.out.println("hitted for get avatar");
		return employeeService.getProfilePicture(id);
	}

	@PostMapping(value = "/emp/login" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseMsg checkLogin(@RequestBody EmpUser user){
		System.out.println(user);
		ResponseMsg message = employeeService.checkAuthentication(user.getEmail(),user.getPassword());
		System.out.println(message);
		return message;
	}

	@PostMapping(value = "/emp/upload/pdf",
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public void uploadPdf(@RequestPart(name = "details") DocumentDetails documentDetails,
						  @RequestPart MultipartFile file){
		System.out.println("hit to upload_pdf");
		System.out.println(documentDetails+" "+file);
		try {
			if(file!=null){
				documentDetails.setFile(file.getBytes());
			}
			System.out.println(documentDetails);
			documentUploadService.saveDoc(documentDetails);
		}catch (Exception e){
			throw new RuntimeException();
		}
	}

	@GetMapping(value = "/emp/upload/{id}")
	public List<DocumentDetails> getAllDoc(@PathVariable(value = "id") long empId){
		System.out.println("hitted on getALlDoc");
		List<DocumentDetails> docList = documentUploadService.getAllDocByEmpId(empId);
		if(docList!=null && !docList.isEmpty()){
			return docList;
		}else {
			return null;
		}

	}

	@GetMapping(value = "/emp/doc/download/{id}")
	public ResponseEntity<Resource> getDocById(@PathVariable(value = "id")long id){
		System.out.println("hitted on getDocById");
		DocumentDetails doc = documentUploadService.getDocById(id);
		if(doc!=null){
			ByteArrayResource resource = new ByteArrayResource(doc.getFile());
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
					.contentLength(resource.contentLength())
					.header(HttpHeaders.CONTENT_DISPOSITION,
							ContentDisposition.attachment()
									.filename("whatever.jpg")
									.build().toString())
					.body(resource);
//			return doc.getFile();
		}else {
			return null;
		}
	}
}
