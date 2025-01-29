import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './admin/sidebar/sidebar.component';
import { NavbarComponent } from './admin/navbar/navbar.component';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { EmployeesComponent } from './admin/employees/employees.component';
import { AddEmployeeComponent } from './admin/employees/add-employee/add-employee.component';
import { ArchiveEmployeeComponent } from './admin/employees/archive-employee/archive-employee.component';

import { AttendanceComponent } from './admin/attendance/attendance.component';
import { ClaimsComponent } from './admin/claims/claims.component';
import { PayrollComponent } from './admin/payroll/payroll.component';
import { AdminComponent } from './admin/admin.component';
import { CardComponent } from './admin/department/card/card.component';
import { DepartmentComponent } from './admin/department/department.component';
import { UserComponent } from './user/user.component';
import { ProfilComponent } from './user/profil/profil.component';
import { CheckinComponent } from './user/userattendance/checkin/checkin.component';
import { CheckoutComponent } from './user/userattendance/checkout/checkout.component';
import { UserRequestsComponent } from './user/user-requests/user-requests.component';
import { UserClaimsComponent } from './user/user-claims/user-claims.component';
import { UserPayrollComponent } from './user/user-payroll/user-payroll.component';
import { HomeComponent } from './user/home/home.component';
import { EditprofilimageComponent } from './user/profil/editprofilimage/editprofilimage.component';
import { UserinfoComponent } from './user/profil/userinfo/userinfo.component';
import { WorkinfoComponent } from './user/profil/workinfo/workinfo.component';
import { HeaderComponent } from './user/header/header.component';
import { UserLeaveComponent } from './user/user-requests/user-leave/user-leave.component';
import { UserResignComponent } from './user/user-requests/user-resign/user-resign.component';
import { ShowDepartsComponent } from './admin/department/show-departs/show-departs.component';
import { DeptEmployeesComponent } from './admin/department/dept-employees/dept-employees.component';
import { HomepageComponent } from './user/homepage/homepage.component';
import { CommonModule } from '@angular/common';
import { ResignationsComponent } from './admin/requests/resignations/resignations.component';
import { LeavesComponent } from './admin/requests/leaves/leaves.component';
import { MyClaimsComponent } from './user/user-claims/my-claims/my-claims.component';
import { MyLeavesComponent } from './user/user-requests/user-leave/my-leaves/my-leaves.component';
import { MyResignsComponent } from './user/user-requests/user-resign/my-resigns/my-resigns.component';



@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    NavbarComponent,
    DashboardComponent,
    LoginComponent,
    EmployeesComponent,
    AddEmployeeComponent,
    ArchiveEmployeeComponent,
    DepartmentComponent,
    CardComponent,
    AttendanceComponent,
    ClaimsComponent,
    PayrollComponent,
    AdminComponent,
    UserComponent,
    ProfilComponent,
    CheckinComponent,
    CheckoutComponent,
    UserRequestsComponent,
    UserClaimsComponent,
    UserPayrollComponent,
    HomeComponent,
    EditprofilimageComponent,
    UserinfoComponent,
    WorkinfoComponent,
    HeaderComponent,
    UserLeaveComponent,
    UserResignComponent,
    ShowDepartsComponent,
    DeptEmployeesComponent,
    HomepageComponent,
    ResignationsComponent,
    LeavesComponent,
    AttendanceComponent,
    MyClaimsComponent,
    MyLeavesComponent,
    MyResignsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
