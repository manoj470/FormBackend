package com.clover.form.controller;

import com.clover.form.model.Employee;
import com.clover.form.model.FamilyDetailsList;
import com.clover.form.model.PostData;
import com.clover.form.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/emp")
	public List<Employee> viewPage(Model model) {
		System.out.println("api hitted for getAllEmp");
		return employeeService.getAllEmployees();
	}

//	@PostMapping("/emp/add_new")
//	public void showNewEmployeeForm(@RequestBody Employee employee) {
//		System.out.println("api hitted for addNewEmp");
////		try {
////			employee.setAvatar(employee.getFile().getBytes());
////		} catch (IOException e) {
////			throw new RuntimeException(e);
////		}
//		System.out.println(employee);
//		employeeService.saveEmployee(employee);
//	}

	@PostMapping(value = "/emp/add_new",
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public void showNewEmployeeForm(@RequestPart Employee employee,
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
//		System.out.println(employee.getImage());
//		employee.setAvatar(employee.getImage().getBytes());
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
}
