import { EmployeeService } from './../employee.service';
import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {
  employee : Employee = new Employee();
  constructor(private empService: EmployeeService,private router:Router) { }

  ngOnInit(): void {
  }

  saveEmployee(){
    this.empService.employeeAdd(this.employee).subscribe(data=>{
      console.log(data);
      this.goToEmpList();
    },error=>console.log(error));
  }

  goToEmpList(){
    this.router.navigate(['/employees']);
  }

  onSubmit(){
    this.saveEmployee();
  }
}
