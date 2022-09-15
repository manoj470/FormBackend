package com.clover.form.service;

import com.clover.form.miscellaneous.ExtendedService;
import com.clover.form.model.Employee;
import com.clover.form.model.ResponseMsg;
import com.clover.form.repository.EmployeePaginationRepo;
import com.clover.form.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeePaginationRepo employeePaginationRepo;

	@Override
	public List<Employee> getAllEmployees() {
//		return ExtendedService.setNull(employeeRepository.findAll());
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(long id) {
		List<Employee> employeeList = employeeRepository.findAllById(Collections.singleton(id));
		if(!employeeList.isEmpty()){
			if(employeeList.get(0)!= null) {
				return ExtendedService.setDataProper(employeeList.get(0));
			}else {
				throw new RuntimeException(" Employee not found for id :: " + id);
			}
		}else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
//		Optional<Employee> optional = employeeRepository.findById(id);
//		Employee employee = null;
//		if (optional.isPresent()) {
//			employee = optional.get();
//		} else {
//			throw new RuntimeException(" Employee not found for id :: " + id);
//		}
	}

	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);

	}

	@Override
	public void setProfilePicture(long id, MultipartFile file) {
		Employee employee = null;
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isPresent()){
			employee = optional.get();
			try {
				employee.setAvatar(file.getBytes());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			employeeRepository.save(employee);
		}
	}

	@Override
	public byte[] getProfilePicture(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		return optional.map(Employee::getAvatar).orElse(null);
	}

	@Override
	public ResponseMsg checkAuthentication(String emailId, String pwd) {
		if(emailId==null){
			return new ResponseMsg(null,"Oops! Email id is empty");
		}else if(pwd==null){
			return new ResponseMsg(null,"Oops! password is empty");
		}
		List<Employee> empList = employeeRepository.findEmpByEmail(emailId);
		System.out.println(">>>>>>> "+empList);
		if(!empList.isEmpty()){
			List<Employee> list = empList.stream()
					.filter(e-> Objects.equals(e.getPassword(), pwd))
					.collect(Collectors.toList());
			if(!list.isEmpty()){
				Employee employee = list.get(0);
				return new ResponseMsg(employee.getId(),"Login Successfully!");
			}
		}
		return new ResponseMsg(null,"Invalid User!");
	}

	@Override
	public List<Employee> findPaginatedByCity(int pageNo, int pageSize,String city) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Employee> pagedResult = employeePaginationRepo.findAllByCity(city,paging);
		return pagedResult.toList();
	}

	@Override
	public List<Employee> findPaginated(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Employee> pagedResult = employeePaginationRepo.findAll(paging);
		return pagedResult.toList();
	}

	@Override
	public Long getEmployeeCount() {
		try {
			return employeeRepository.count();
		}catch (Exception exception){
			System.out.println("Error "+exception);
		}
		return null;
	}
}
