import { Component } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { LogoutService } from 'src/app/service/logout.service';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  constructor(
    private logoutService: LogoutService,
    private authService: AuthService
  ) {}

  isEmployeesDropdownOpen = false;
  isAttendanceDropdownOpen = false;
  isRequestsDropdownOpen = false;

  toggleEmployeesDropdown() {
    this.isEmployeesDropdownOpen = !this.isEmployeesDropdownOpen;
    this.isRequestsDropdownOpen = false;
    this.isAttendanceDropdownOpen = false;
  }

  toggleAttendanceDropdown() {
    this.isAttendanceDropdownOpen = !this.isAttendanceDropdownOpen;
    this.isRequestsDropdownOpen = false;
    this.isEmployeesDropdownOpen = false;
  }

  toggleRequestsDropdown() {
    this.isRequestsDropdownOpen = !this.isRequestsDropdownOpen;
    this.isAttendanceDropdownOpen = false;
    this.isEmployeesDropdownOpen = false;
  }

  get employeeId(): string | null {
    return this.authService.getUserId();
  }

  logout(): void {
    this.authService.logout();
  }
}
