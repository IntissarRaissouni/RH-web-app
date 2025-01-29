// auth.service.ts

import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private userIdKey = 'userId';
  constructor(private router: Router) {}
  token = localStorage.getItem('jwt');

  setUserId(userId: string): void {
    if (userId) {
      localStorage.setItem(this.userIdKey, userId);
    } else {
      localStorage.removeItem(this.userIdKey);
    }
  }

  getUserId(): string | null {
    return localStorage.getItem(this.userIdKey);
  }

  isAuthenticated(): boolean {
    if (this.token) {
      return true;
    } else {
      return false;
    }
  }

  decodeJwt(token: string): any {
    try {
      const base64Url = token.split('.')[1];
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      return JSON.parse(atob(base64));
    } catch (e) {
      return {};
    }
  }
  
  logout(): void {
    localStorage.removeItem('jwt');
    localStorage.removeItem('userId');
    this.router.navigateByUrl('/login');
  }
  // setAdminId(id: string) {
  //   this.adminId = id;
  // }

  // getAdminId(): string | null {
  //   return this.adminId;
  // }

  // hasRole(expectedRole: string): boolean {
  //   // Example: Check if the user has the expected role
  //   const userRoles: string[] = /* Retrieve user roles from your authentication service */;
  //   return userRoles.includes(expectedRole);
  // }
}
