package com.lta.mgmtclients.Controller;

import com.lta.mgmtclients.Model.Employee;
import com.lta.mgmtclients.Report.EmployeeReportEXCEL;
import com.lta.mgmtclients.Report.EmployeeReportPDF;
import com.lta.mgmtclients.Service.EmployeeService;
import com.lta.mgmtclients.Util.Pagination.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService es;

    @GetMapping({"/","/employees",""})
    public String getEmployees(@RequestParam(name = "page",defaultValue = "0") int page, Model model ){
        Pageable pageRequest = PageRequest.of(page,5);
        Page<Employee> employees = es.findAll(pageRequest);
        PageRender<Employee> pageRender = new PageRender<>("/employees",employees);
        model.addAttribute("title","Employee List");
        model.addAttribute("employees",employees);
        model.addAttribute("page",pageRender);
    return "list";
    }

    @GetMapping("/employees/{idE}")
    public String getEmployee(@PathVariable Long idE, Map<String,Object> model, RedirectAttributes flash){
        Employee employee = es.findById(idE);
        if(employee == null){
            flash.addFlashAttribute("error","Not found Employee with inserted id");
            return "list";
        }
        model.put("employee",employee);
        model.put("title","Details Employee: "+ employee.getName() + employee.getSurname());
    return "detail";
    }

    @GetMapping("/form")
    public String viewRegEmployee(Map<String,Object> model){
        Employee employee = new Employee();
        model.put("employee",employee);
        model.put("title","Register Employee");
        return "form";
    }

    @PostMapping("/register")
    public String registerEmployee(@Valid Employee employee, BindingResult br,
                                   Model model, RedirectAttributes flash, SessionStatus status){
        if(br.hasErrors()){
            model.addAttribute("title","Register Employee");
            return "form";
        }
        String message = (employee.getId() != null ? "Edited Employee" : "Registered employee" );
        es.save(employee);
        status.setComplete();
        flash.addFlashAttribute("success",message);
    return "redirect: list";
    }

    @GetMapping("/edit/{idE}")
    public String viewEditEmployee(Map<String,Object> model,@PathVariable Long idE
                                    ,RedirectAttributes flash){
        if(idE>0){
            Employee employee = es.findById(idE);
            if(employee == null){
                flash.addFlashAttribute("error","Not found employee with id");
                return "redirect:/list";
            }
        }else{
            flash.addFlashAttribute("error","Invalid ID entered");
        }
        model.put("employee",new Employee());
        model.put("title","Edit Employee");
        return "form";
    }

    @GetMapping("/expPDF")
    public void exportPDFReport(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String date = format.format(new Date());

        String header = "Content-Disposition";
        String value = "attachment; filename= Employees_" + date + ".pdf";
        response.setHeader(header,value);

        List<Employee> employees = es.findAll();

        EmployeeReportPDF reportPDF = new EmployeeReportPDF(employees);
        reportPDF.export(response);
    }

    @GetMapping("/expEX")
    public void exportEXCELReport(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String date = format.format(new Date());

        String header = "Content-Disposition";
        String value = "attachment; filename= Employees_" + date + ".xlsx";
        response.setHeader(header,value);

        List<Employee> employees = es.findAll();

        EmployeeReportEXCEL reportEXCEL = new EmployeeReportEXCEL(employees);
        reportEXCEL.export(response);
    }

}
