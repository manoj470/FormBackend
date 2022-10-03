package com.clover.form.miscellaneous;

import com.clover.form.model.Employee;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CSVHelper {

    public static ByteArrayInputStream toCSV(List<Employee> employeeList) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(new PrintWriter(out), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Name", "E-mail", "Hobbies", "Gender", "Date of Birth","panNumber"};
        String[] nameMapping = {"employeeName", "email", "hobbies", "gender", "dateOfBirth","panNumber"};

        csvWriter.writeHeader(csvHeader);

        for (Employee employee : employeeList) {
            csvWriter.write(employee, nameMapping);
        }

        csvWriter.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
