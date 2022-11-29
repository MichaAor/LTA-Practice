import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from './../employee.service';
import { Employee } from '../employee';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {
  id:number;
  employee:Employee;

  constructor(private empService: EmployeeService,private router:Router,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.empService.employeeGet(this.id).subscribe(data=>{
      this.employee = data;
    },error=>console.log(error));
  }

  goToEmpList(){
    this.router.navigate(['/employees']);
  }

  onSubmit(){
    this.empService.employeeUpd(this.employee,this.id).subscribe(data=>{
      this.goToEmpList();
    },error=>console.log(error));
  }
}
