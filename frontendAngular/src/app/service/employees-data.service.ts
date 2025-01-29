import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EmployeesDataService {
  private apiUrl = 'http://localhost:8087/admin';

  constructor(private http: HttpClient) {}

  private employeesSource = new BehaviorSubject<any[]>([]);
  employees$ = this.employeesSource.asObservable();
  setEmployees(employees: any[]): void {
    this.employeesSource.next(employees);
  }

  private deptIdSource = new BehaviorSubject<string>('');
  deptId$ = this.deptIdSource.asObservable();
  setDeptId(deptId: string): void {
    this.deptIdSource.next(deptId);
  }

  addEmployee(adminId: string, departmentId: string, employeeData: any): Observable<any> {
    const url = `${this.apiUrl}/${adminId}/departement/${departmentId}/employee/add`;
    return this.http.post(url, employeeData);
  }
}
