import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class RequestsService {
  private apiUrl = 'http://localhost:8087/admin';

  constructor(private http: HttpClient, private authService: AuthService) {}
  adminId = this.authService.getUserId();

  getResignations(): Observable<any> {
    const url = `${this.apiUrl}/${this.adminId}/request/resing/getAll`;
    return this.http.get(url);
  }

  reject(requestId: string): Observable<any> {
    const url = `${this.apiUrl}/${this.adminId}/request/resign/refuse/${requestId}`;
    return this.http.put(url, {});
  }

  approve(requestId: string): Observable<any> {
    const url = `${this.apiUrl}/${this.adminId}/request/resign/accept/${requestId}`;
    return this.http.put(url, {});
  }

  getLeaves(): Observable<any> {
    const url = `${this.apiUrl}/${this.adminId}/request/leave/getAll`;
    return this.http.get(url);
  }

  approveLeave(leaveId: string): Observable<any> {
    const url = `${this.apiUrl}/${this.adminId}/request/vacation/accept/${leaveId}`;
    return this.http.put(url, {});
  }
  rejectLeave(leaveId: string): Observable<any> {
    const url = `${this.apiUrl}/${this.adminId}/request/vacation/refuse/${leaveId}`;
    return this.http.put(url, {});
  }
  getClaims(): Observable<any> {
    const url = `${this.apiUrl}/${this.adminId}/reclamations`;
    return this.http.get(url);
  }
  getAttandences(): Observable<any>{
    const url = `${this.apiUrl}/${this.adminId}/attendances`;
    return this.http.get(url);
  }
}
