package com.lta.mgmtclients.Report;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lta.mgmtclients.Model.Employee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReportPDF {
    private List<Employee> empList;

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NAME",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SURNAME",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("EMAIL",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("PHONE",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SEX",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SALARY",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("DATE",font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table){
        for(Employee employee : empList){
            table.addCell(String.valueOf(employee.getId()));
            table.addCell(employee.getName());
            table.addCell(employee.getSurname());
            table.addCell(employee.getEmail());
            table.addCell(employee.getPhone());
            table.addCell(employee.getSex());
            table.addCell(String.valueOf(employee.getSalary()));
            table.addCell(employee.getDate().toString());
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLUE);
        font.setSize(18);

        Paragraph title = new Paragraph("Employee List",font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{1f,2.3f,2.3f,6f,2.9f,3.5f,2f,2.2f});
        table.setWidthPercentage(110);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
