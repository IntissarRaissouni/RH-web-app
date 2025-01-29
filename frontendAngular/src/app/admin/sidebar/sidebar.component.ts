import { Component, Input } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { LogoutService } from 'src/app/service/logout.service';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent {
  http: any;
  constructor(
    private logoutService: LogoutService,
    private authService: AuthService
  ) {}

  get adminId(): string | null {
    return this.authService.getUserId();
  }

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

  logout(): void {
    this.authService.logout();
  }
}
