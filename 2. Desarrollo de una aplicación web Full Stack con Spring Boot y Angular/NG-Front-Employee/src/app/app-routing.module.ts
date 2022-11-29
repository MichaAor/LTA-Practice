import { ViewEmployeeComponent } from './view-employee/view-employee.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { ListEmployeeComponent } from './list-employee/list-employee.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: 'employees', component:ListEmployeeComponent},
  {path: 'add-employee', component:AddEmployeeComponent},
  {path: 'upd-employee/:id', component:UpdateEmployeeComponent},
  {path: 'view-employee/:id', component:ViewEmployeeComponent},
  {path: '', redirectTo: 'employees',pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
