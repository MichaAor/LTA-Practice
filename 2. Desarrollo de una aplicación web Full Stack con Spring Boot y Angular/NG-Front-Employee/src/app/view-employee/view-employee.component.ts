import { EmployeeService } from './../employee.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';

@Component({
  selector: 'app-view-employee',
  templateUrl: './view-employee.component.html',
  styleUrls: ['./view-employee.component.css']
})
export class ViewEmployeeComponent implements OnInit {
  private id: number;
  emp: Employee;
  constructor(private router:ActivatedRoute,private empService:EmployeeService ) {}

  ngOnInit(): void {
    this.id = this.router.snapshot.params['id'];
    this.emp = new Employee();
    this.empService.employeeGet(this.id).subscribe(data=>{
      this.emp = data;
    });
  }
}
