package com.clover.form.service;

import com.clover.form.model.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	void updateEmployee(Employee employee);
	void setProfilePicture(long id, MultipartFile file);
	byte[] getProfilePicture(long id);
}
