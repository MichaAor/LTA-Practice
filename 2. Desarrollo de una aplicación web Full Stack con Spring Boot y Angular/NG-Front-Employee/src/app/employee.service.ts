import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private baseURL = 'http://localhost:8080/api/employees';

  constructor(private httpClient: HttpClient) { }

  employeeGetAll():Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(this.baseURL);
  }

  employeeAdd(emp:Employee):Observable<Object>{
    return this.httpClient.post(this.baseURL,emp);
  }

  employeeGet(id:number):Observable<Employee> {
    return this.httpClient.get<Employee>(this.baseURL+'/'+id);
  }

  employeeUpd(emp:Employee,id:number):Observable<Object>{
    return this.httpClient.put(this.baseURL+'/'+id,emp);
  }

  employeeDel(id:number):Observable<Object>{
    return this.httpClient.delete(this.baseURL+'/'+id);
  }
}
