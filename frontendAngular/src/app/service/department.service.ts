import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DepartmentService {
  private apiUrl = 'http://localhost:8087/admin';

  constructor(private http: HttpClient) { }

  addDepartment(adminId: string, data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/${adminId}/departement/add`, data);
  }

  getDepartments(adminId: string): Observable<any[]> {
    const url = `${this.apiUrl}/${adminId}/departement/getAll`;
    return this.http.get<any[]>(url);
  }
  deleteDepartment(adminId: string, departmentId: string): Observable<any> {
    const url = `${this.apiUrl}/${adminId}/departement/delete/${departmentId}`;
    return this.http.delete(url);
  }
  showdepartEmployees(adminId: string, departmentId: string): Observable<any> {
    const url = `${this.apiUrl}/${adminId}/departement/${departmentId}/employee/getALL`;
    return this.http.get<any[]>(url);
  }
  editEmployee(adminId: string, departmentId: string, emplId: string, newdata: any): Observable<any> {
    const url = `${this.apiUrl}/${adminId}/departement/${departmentId}/employee/edit/${emplId}`;
    return this.http.put(url,newdata).pipe(
      catchError((error) => {
        console.error('Error updating employee:', error);
        return throwError(error);
      })
    );
  }
  deleteEmployee(adminId: string, departmentId: string, emplId: string): Observable<any> {
    const url = `${this.apiUrl}/${adminId}/departement/${departmentId}/employee/delete/${emplId}`;
    return this.http.delete(url).pipe(
      catchError((error) => {
        console.error('Error deleting employee:', error);
        return throwError(error);
      })
    );
  }
}
