package com.clover.form.service;

import com.clover.form.model.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class ExcelExporterImpl implements ExcelExporter{

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Employee> employeeList;

    public ExcelExporterImpl(List<Employee> employeeList) {
        this.employeeList = employeeList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Employees");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "employee_name", style);
        createCell(row, 1, "date_of_birth", style);
        createCell(row, 2, "email", style);
        createCell(row, 3, "password", style);
        createCell(row, 4, "gender", style);
        createCell(row, 5, "hobbies", style);
        createCell(row, 6, "address_line1", style);
        createCell(row, 7, "address_line2", style);
        createCell(row, 8, "zip_code", style);
        createCell(row, 9, "city", style);
        createCell(row, 10, "country", style);
        createCell(row, 11, "pan_number", style);
//        createCell(row, 12, "avatar", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        cell.setCellValue((String) value);
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Employee employee : employeeList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, 0, employee.getEmployeeName(), style);
            createCell(row, 1, employee.getDateOfBirth(), style);
            createCell(row, 2, employee.getEmail(), style);
            createCell(row, 3, employee.getPassword(), style);
            createCell(row, 4, employee.getGender(), style);
            createCell(row, 5, employee.getHobbies(), style);
            createCell(row, 6, employee.getAddressLine1(), style);
            createCell(row, 7, employee.getAddressLine2(), style);
            createCell(row, 8, employee.getZipCode(), style);
            createCell(row, 9, employee.getCity(), style);
            createCell(row, 10, employee.getCountry(), style);
            createCell(row, 11, employee.getPanNumber(), style);
//            createCell(row, 12, employee.getAvatar(), style);

        }
    }

    @Override
    public void export() throws IOException {
        writeHeaderLine();
        writeDataLines();

//        ServletOutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//
//        outputStream.close();
    }
}
