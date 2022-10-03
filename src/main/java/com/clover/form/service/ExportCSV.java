package com.clover.form.service;

import com.clover.form.miscellaneous.CSVHelper;
import com.clover.form.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExportCSV {

    @Autowired
    EmployeeServiceImpl employeeService;

    public ByteArrayInputStream load() throws IOException {
        List<Employee> employeeList = employeeService.getAllEmployees();
        System.out.println("query all data....");
        return CSVHelper.toCSV(employeeList);
    }
}
