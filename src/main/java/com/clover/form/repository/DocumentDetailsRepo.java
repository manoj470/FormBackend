package com.clover.form.repository;

import com.clover.form.model.DocumentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DocumentDetailsRepo extends JpaRepository<DocumentDetails,Long> {
    @Query(value = "select * from EMP_DOC where emp_id=?1",nativeQuery = true)
    List<DocumentDetails> findAllByEmpId(@Param("emp_id") long empId);
}
