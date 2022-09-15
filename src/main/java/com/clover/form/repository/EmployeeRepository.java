package com.clover.form.repository;

import com.clover.form.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

//    @Query(value = "SELECT * FROM EMPLOYEE as a join FAMILY_DETAILS  as b on a.id=b.EMPLOYEE_ID ",nativeQuery = true)
//    List<Employee> findAllEmployee();

    @Query(value = "SELECT * FROM employee where email=?1",nativeQuery = true)
    List<Employee> findEmpByEmail(@Param(value = "email") String email);

    Employee findByEmail(String email);

    List<Employee> findFirst100ById(int salary);



}
