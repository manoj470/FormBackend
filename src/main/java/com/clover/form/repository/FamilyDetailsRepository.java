package com.clover.form.repository;

import com.clover.form.model.FamilyDetailsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FamilyDetailsRepository extends JpaRepository<FamilyDetailsList,Long> {

//    @Query(value = "SELECT * FROM FAMILY_DETAILS where EMPLOYEE_ID =?1",nativeQuery = true)
//    List<FamilyDetailsList> findAllByEmployeeId(@Param("EMPLOYEE_ID")long Emp_id);
}
