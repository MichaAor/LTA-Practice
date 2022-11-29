import { EmployeeService } from './../employee.service';
import { Employee } from './../employee';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
	selector: 'app-list-employee',
	templateUrl: './list-employee.component.html',
	styleUrls: ['./list-employee.component.css']
})
export class ListEmployeeComponent implements OnInit {
	emps: Employee[];

	constructor(private empService:EmployeeService,private router:Router) { }

	ngOnInit(): void {
    this.employeeGetAll();
	}

  private employeeGetAll(){
    this.empService.employeeGetAll().subscribe(data => {
      this.emps = data;
    });
  }

  employeeGet(id: number){
    this.router.navigate(['view-employee',id]);
  }

  employeeUpd(id: number){
    this.router.navigate(['upd-employee',id]);
  }

  employeeDel(id:number){
    this.empService.employeeDel(id).subscribe(data=>{
      console.log(data);
      this.employeeGetAll();
    });
  }



}
