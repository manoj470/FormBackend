package com.clover.form.repository;

import com.clover.form.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePaginationRepo extends PagingAndSortingRepository<Employee,Long> {

    Page<Employee> findAllByCity(String type, Pageable pageable);
}
