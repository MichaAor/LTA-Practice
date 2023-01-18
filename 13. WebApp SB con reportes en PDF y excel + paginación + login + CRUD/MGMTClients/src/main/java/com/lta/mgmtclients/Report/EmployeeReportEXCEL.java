package com.lta.mgmtclients.Report;

import com.lta.mgmtclients.Model.Employee;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@NoArgsConstructor
public class EmployeeReportEXCEL {
    private XSSFWorkbook book;
    private XSSFSheet sheet;
    private List<Employee> employees;

    public EmployeeReportEXCEL(List<Employee> employees) {
        this.book = new XSSFWorkbook();
        this.sheet = book.createSheet("Employees");
        this.employees = employees;
    }

    private void writeTableHeader(){
        XSSFRow row = sheet.createRow(0);
        CellStyle style = book.createCellStyle();
        XSSFFont font = book.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        XSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("NAME");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("SURNAME");
        cell.setCellStyle(style);


        cell = row.createCell(3);
        cell.setCellValue("EMAIL");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("PHONE");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("SEX");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("SALARY");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("DATE");
        cell.setCellStyle(style);
    }

    public void writeTableData(){
        int numRow = 1;
        CellStyle style = book.createCellStyle();
        XSSFFont font = book.createFont();

        font.setFontHeight(14);
        style.setFont(font);

        for(Employee employee : employees){
            XSSFRow row = sheet.createRow(numRow ++);

            XSSFCell cell = row.createCell(0);
            cell.setCellValue(employee.getId());
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(employee.getName());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(employee.getSurname());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(employee.getEmail());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(employee.getPhone());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(employee.getSex());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);

            cell = row.createCell(6);
            cell.setCellValue(employee.getSalary());
            sheet.autoSizeColumn(6);
            cell.setCellStyle(style);

            cell = row.createCell(7);
            cell.setCellValue(employee.getDate().toString());
            sheet.autoSizeColumn(7);
            cell.setCellStyle(style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeTableHeader();
        writeTableData();

        ServletOutputStream stream = response.getOutputStream();
        book.write(stream);
        book.close();
        stream.close();
    }
}
