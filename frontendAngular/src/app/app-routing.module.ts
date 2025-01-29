import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { AddEmployeeComponent } from './admin/employees/add-employee/add-employee.component';
import { AttendanceComponent } from './admin/attendance/attendance.component';
import { ClaimsComponent } from './admin/claims/claims.component';
import { PayrollComponent } from './admin/payroll/payroll.component';
import { DepartmentComponent } from './admin/department/department.component';
import { LeavesComponent } from './admin/requests/leaves/leaves.component';
import { ResignationsComponent } from './admin/requests/resignations/resignations.component';
import { LoginComponent } from './login/login.component';
import { ArchiveEmployeeComponent } from './admin/employees/archive-employee/archive-employee.component';
import { HomeComponent } from './user/home/home.component';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { UserClaimsComponent } from './user/user-claims/user-claims.component';
import { UserPayrollComponent } from './user/user-payroll/user-payroll.component';
import { UserRequestsComponent } from './user/user-requests/user-requests.component';
import { CheckinComponent } from './user/userattendance/checkin/checkin.component';
import { CheckoutComponent } from './user/userattendance/checkout/checkout.component';
import { ProfilComponent } from './user/profil/profil.component';
import { UserLeaveComponent } from './user/user-requests/user-leave/user-leave.component';
import { UserResignComponent } from './user/user-requests/user-resign/user-resign.component';
import { AuthGuard } from './guards/auth.guard';
import { ShowDepartsComponent } from './admin/department/show-departs/show-departs.component';
import { DeptEmployeesComponent } from './admin/department/dept-employees/dept-employees.component';
import { AdminRoleGuard } from './guards/admin-role.guard';
import { EmployeeRoleGuard } from './guards/employee-role.guard';
import { HomepageComponent } from './user/homepage/homepage.component';
import { MyClaimsComponent } from './user/user-claims/my-claims/my-claims.component';
import { MyLeavesComponent } from './user/user-requests/user-leave/my-leaves/my-leaves.component';
import { MyResignsComponent } from './user/user-requests/user-resign/my-resigns/my-resigns.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: 'admin/:adminId',
    component: AdminComponent,
    canActivate: [AuthGuard, AdminRoleGuard],
    data: { expectedRole: 'ROLE_ADMIN' },
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'employees/add-employee', component: AddEmployeeComponent },
      {
        path: 'employees/archive-employee',
        component: ArchiveEmployeeComponent,
      },
      { path: 'department', component: DepartmentComponent },
      { path: 'show-departments', component: ShowDepartsComponent },
      {
        path: 'show-departments/:departmentId/employees/getALL',
        component: DeptEmployeesComponent,
      },
      { path: 'attendance', component: AttendanceComponent },
      { path: 'requests/leaves', component: LeavesComponent },
      { path: 'requests/resignations', component: ResignationsComponent },
      { path: 'claims', component: ClaimsComponent },
      { path: 'payroll', component: PayrollComponent },
    ],
  },
  {
    path: 'employee/:employeeId',
    component: UserComponent,
    canActivate:[AuthGuard, ],
    children: [
      { path: 'home', component: HomeComponent },
      { path:'homepage',component:HomepageComponent},
      { path: 'claims', component: UserClaimsComponent },
      { path: 'my-claims', component: MyClaimsComponent },
      { path: 'payroll', component: UserPayrollComponent },
      { path: 'checkin', component: CheckinComponent },
      { path: 'checkout', component: CheckoutComponent },
      { path: 'profile', component: ProfilComponent },
      { path: 'requests/leave', component: UserLeaveComponent },
      { path: 'requests/leave/my-leaves', component: MyLeavesComponent },
      { path: 'requests/resign', component: UserResignComponent },
      { path: 'requests/resign/my-resign', component: MyResignsComponent },
      { path: '**', redirectTo: 'homepage' },
    ],
  },
  { path: 'login', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
