package com.clover.form;

import com.clover.form.model.Employee;
import com.clover.form.model.FamilyDetailsList;
import com.clover.form.repository.EmployeeRepository;
import com.clover.form.repository.FamilyDetailsRepository;
import com.clover.form.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class FormApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FormApplication.class, args);
	}

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	EmployeeRepository employeeRepository;
//	@Autowired
//	private FamilyService fm;
	@Autowired
	FamilyDetailsRepository familyDetailsRepository;

	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee();
		employee.setEmployeeName("ok");
		employee.setAddressLine1("qq");
		employee.setCity("q");
		employee.setCountry("w");
		employee.setAddressLine2("q");
		employee.setEmail("ee");
		employee.setGender("q");
		FamilyDetailsList data1 = new FamilyDetailsList();
		data1.setGender("q");
		data1.setContactNumber(234);
		data1.setRelation("w");
//		data1.setEmployee(employee);
		FamilyDetailsList data2 = new FamilyDetailsList();
		data2.setGender("q");
		data2.setContactNumber(234);
		data2.setRelation("z");
//		data2.setEmployee(employee);
//		List<FamilyDetails> familyDetailsList = new ArrayList<>();
//		familyDetailsList.add(data1);
//		familyDetailsList.add(data2);
		employee.add( data1);
		employee.add( data2);
		System.out.println("saving start");
//		System.out.println(employee.getFamilyDetailsList());
//		employeeRepository.save(employee);
		System.out.println("saved.............");
		System.out.println(employeeRepository.findAll());
//		Thread.sleep(100000);
//		fm.getAllFamilyMembersByEmployeeId(1)
//		Employee data3 = employeeService.getEmployeeById(1);
//		List<Employee> dataList = employeeService.getAllEmployees();
//		for (Employee emp:dataList) {
//			emp.setFamilyDetailsList(null);
//		}
//		System.out.println(dataList);
//		Optional<Employee> employee1 = employeeRepository.findById(1L);
//		System.out.println("@@@@@@@@@@@@@");
//		System.out.println(employee1);
//		List<FamilyDetails> familyDetailsList = familyDetailsRepository.findAllByEmployeeId(1);
//		System.out.println(familyDetailsList);
//		System.out.println("query success");
//		dataList.forEach((e)->{
//			System.out.println("Employee Id: "+e.getId());
//			e.getFamilyDetailsList().forEach((f)->{
//				System.out.println("Employee data: "+f.getGender());
//			});
//		});
//		dataList.forEach(f->f.setFamilyDetailsList(null));

//		System.out.println(dataList);
//		for (Employee emp:dataList) {
//			List<FamilyDetails> familyDetailsList = new ArrayList<>();
//			for (FamilyDetails f:emp.getFamilyDetailsList()) {
//				FamilyDetails m = new FamilyDetails();
//				m.setName(f.getName());
//				m.setRelation(f.getRelation());
////				m.setEmployee(f.getEmployee());
//				m.setContactNumber(f.getContactNumber());
//				m.setGender(f.getGender());
//				m.setId(f.getId());
//				m.setDateOfBirth(f.getDateOfBirth());
//				familyDetailsList.add(m);
//			}
//			emp.setFamilyDetailsList(familyDetailsList);
////			System.out.println("inside loop");
////			System.out.println(emp.getFamilyDetailsList().size());
////			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
////			String json = ow.writeValueAsString(emp);
////			System.out.println(json);
////			System.out.println("end......");
////			FamilyDetails m1 = new FamilyDetails();
////			m1.setName(emp.getFamilyDetailsList().get(5).getName());
////			familyDetailsList.add(m1);
//		}
//		System.out.println(dataList);
//		System.out.println("start......");
//		System.out.println(employeeRepository.findAllEmployee());

//		for (Employee emp:dataList) {
//			emp.setFamilyDetailsList(familyDetailsList);
//		}
//		List<Object> dest = new ArrayList<>();
////		System.out.println(dataList);
//		Collections.copy(dest,dataList);
//		System.out.println(dest);

//		List<Employee> copy = new ArrayList<>(dataList);
//		System.out.println(copy);



//		System.out.println(test);

//		System.out.println(dataList);
//		System.out.println(dataList);
//		System.out.println("@@@@@@@@@@@@@@");
//		System.out.println(data3.toString());
//		employeeService.deleteEmployeeById(1);
//		System.out.println("deleted");
//		System.out.println("on sleep");
//		Thread.sleep(100000);
//		System.out.println("complete sleep");
//		familyDetailsList.get(0).setName("qwertyuio");
//		employee.setFamilyDetailsList(familyDetailsList);
//		employeeService.saveEmployee(employee);

	}
}
