package com.clover.form.controller;

import com.clover.form.miscellaneous.ExtendedService;
import com.clover.form.model.DocumentDetails;
import com.clover.form.model.EmpUser;
import com.clover.form.model.Employee;
import com.clover.form.model.ResponseMsg;
import com.clover.form.security.AuthRequest;
import com.clover.form.security.AuthResponse;
//import com.clover.form.security.JWTUtil;
//import com.clover.form.security.MyUserDetailsService;
import com.clover.form.service.DocumentUploadService;
import com.clover.form.service.EmployeeService;
import com.clover.form.service.ExportCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
//@RequestMapping("/emp")
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
	public ResponseMsg uploadFile(@RequestPart(name = "details") DocumentDetails documentDetails,
						  @RequestPart MultipartFile file){
		System.out.println("hit to upload_pdf");
		return documentUploadService.saveDoc(documentDetails,file);
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
			return ResponseEntity.ok()
					.contentType(ExtendedService.getMediaType(doc.getType()))
					.body(resource);
		}else {
			return null;
		}
	}

	@DeleteMapping(value = "/emp/doc/delete/{id}")
	public ResponseMsg deleteByDocId(@PathVariable(value = "id")long id){
		System.out.println("hit on deleteByDocId");
		return documentUploadService.deleteDocById(id);
	}


	@GetMapping(value= "/emp/{pageNo}/{pageSize}/{city}")
	public List<Employee> getPaginatedDataByCity(@PathVariable int pageNo,
											   @PathVariable int pageSize,
												@PathVariable String city) {

		return employeeService.findPaginatedByCity(pageNo, pageSize,city);
	}

	@GetMapping(value= "/emp/{pageNo}/{pageSize}")
	public List<Employee> getPaginatedAllData(@PathVariable int pageNo,
												@PathVariable int pageSize) {

		return employeeService.findPaginated(pageNo, pageSize);
	}

	@GetMapping(value= "/emp/count")
	public Long getEmployeeCount() {
		return employeeService.getEmployeeCount();
	}

	@Autowired
	ExportCSV fileService;

	@GetMapping("/emp/export/csv")
	public ResponseEntity<Resource> getCSV() throws IOException {
		System.out.println("hit on getCSV...");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String currentDateTime = dateFormatter.format(new Date());
		String filename = "Employee"+currentDateTime+".csv";
		InputStreamResource file = new InputStreamResource(fileService.load());
		System.out.println("files ready......");
		System.out.println("ok");
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv"))
				.body(file);
	}

	@GetMapping(value = "/emp/search/{data}")
	public List<Employee> searchByData(@PathVariable("data") String query){
		return employeeService.searchRowsByFieldData(query);
	}

//	@Autowired
//	AuthenticationManager authenticationManager;
//	@Autowired
//	MyUserDetailsService userDetailsService;
//	@Autowired
//	JWTUtil jwtUtil;

//	@PostMapping(value = "/emp/sign_in")
//	public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest)throws Exception{
//		System.out.println("Called.............................");
//		System.out.println("create auth token......"+authRequest);
//		if(authRequest.getEmail()==null){
//			return ResponseEntity.ok()
//					.body(new AuthResponse("Oops! Email id is empty"));
//		}else if(authRequest.getPassword()==null){
//			return ResponseEntity.ok()
//					.body(new AuthResponse("Oops! password is empty"));
//		}
//		Authentication authentication;
//		try {
//			authentication = authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
//							authRequest.getPassword())
//			);
//		}catch (BadCredentialsException ex){
//			throw new Exception("Incorrect username or password ");
//		}
//		final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		System.out.println(userDetails.toString());
//		ResponseCookie jwtCookie = jwtUtil.generateJwtCookie(userDetails);
//		System.out.println("token is: "+jwtCookie);
//		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//				.body(new AuthResponse("Login Successfully!"));
//	}

//	@PostMapping("/emp/sign_out")
//	public ResponseEntity<?> logoutUser() {
//		ResponseCookie cookie = jwtUtil.getCleanJwtCookie();
//		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//				.body(new AuthResponse("You've been signed out!"));
//	}

}
