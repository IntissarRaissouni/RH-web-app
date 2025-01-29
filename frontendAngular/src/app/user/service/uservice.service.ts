import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserviceService {
  private apiUrl = 'http://localhost:8087/employee';

  constructor(private http: HttpClient) {}

  submitClaim(employeeId: string, claimsData: any): Observable<any> {
    const url = `${this.apiUrl}/${employeeId}/claims/add`;
    return this.http.post(url, claimsData);
  }

  checkIn(employeeId: string, checkinData: any): Observable<any> {
    const url = `${this.apiUrl}/${employeeId}/attendance/checkin`;
    return this.http.put(url, checkinData);
  }
  checkOut(employeeId: string, checkoutData: any): Observable<any> {
    const url = `${this.apiUrl}/${employeeId}/attendance/checkout`;
    return this.http.put(url, checkoutData);
  }
  getinfo(employeeId: string): Observable<any> {
    const url = `${this.apiUrl}/${employeeId}/getinfo`;
    return this.http.get(url);
  }
  Resign(employeeId: string, resignData: any): Observable<any> {
    const url = `${this.apiUrl}/${employeeId}/request/resign/add`;
    return this.http.post(url, resignData);
  }
  leave(employeeId: string, leaveData: any): Observable<any> {
    const url = `${this.apiUrl}/${employeeId}/requests/leave/add`;
    return this.http.post(url, leaveData);
  }
  getClaims(employeeId: string): Observable<any> {
    const url = `${this.apiUrl}/${employeeId}/claims/getAll`;
    return this.http.get(url);
  }
  deleteClaim(employeeId: string, claimId: string): Observable<any> {
    const url = `${this.apiUrl}/${employeeId}/claims/delete/${claimId}`;
    return this.http.delete(url);
  }
  getLeaves(employeeId: string): Observable<any> {
    const url = `${this.apiUrl}/${employeeId}/request/leaves`;
    return this.http.get(url);
  }
  deleteLeave(employeeId: string, leaveId: string): Observable<any> {
    const url = `${this.apiUrl}/${employeeId}request/leaves/delete/${leaveId}`;
    return this.http.delete(url);
  }
  getResigns(employeeId: string): Observable<any> {
    const url = `${this.apiUrl}/${employeeId}/request/resigns`;
    return this.http.get(url);
  }
  deleteResign(employeeId: string, resignId: string): Observable<any> {
    const url = `${this.apiUrl}/${employeeId}/request/resign/delete/${resignId}`;
    return this.http.delete(url);
  }
}
