import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class LogoutService {
  private logoutUrl = 'http://localhost:8087/logout';

  constructor(private http: HttpClient) {}

  logout(): Observable<any> {
    return this.http.post(this.logoutUrl, {});
  }
}
