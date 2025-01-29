import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { DepartmentService } from 'src/app/service/department.service';
import { EmployeesDataService } from 'src/app/service/employees-data.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal  from 'sweetalert2';

@Component({
  selector: 'app-dept-employees',
  templateUrl: './dept-employees.component.html',
  styleUrls: ['./dept-employees.component.css'],
})
export class DeptEmployeesComponent implements OnInit {
  employees: any[] = [];
  adminId: string = '';
  deptId: string = '';
  editForm!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private deptService: DepartmentService,
    private employeesDataService: EmployeesDataService,
    private fb: FormBuilder
  ) {}
  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const userId = this.authService.getUserId();
      if (userId) {
        this.adminId = userId;
      }
    });

    this.employeesDataService.employees$.subscribe((employees) => {
      this.employees = employees;
    });

    this.employeesDataService.deptId$.subscribe((id) => {
      this.deptId = id;
    });
    this.editForm = this.fb.group({
      firstName: [''],
      lastName: [''],
      position: [''],
      email: ['', [ Validators.email]],
      phoneNumber: [''],
      address: [''],
    });
  }
  loadEditForm(employee: any) {
    this.editForm.setValue({
      firstName: employee.firstName,
      lastName: employee.lastName,
      position: employee.position,
      email: employee.email,
      phoneNumber: employee.phoneNumber,
      address: employee.address,
    });
  }
  openEditForm(emplId:string,employee: any) {
    Swal.fire({
      title: 'Edit Employee',
      html: this.generateEditFormHtml(employee),
      showConfirmButton: false,
      showCancelButton: true,
      focusConfirm: false,
      preConfirm: () => {
        this.editEmployee(emplId,employee);
      },
      customClass: {
        container: 'edit-form-container', // Add a custom CSS class for the container
        confirmButton: 'btn btn-danger', // Style the confirm button
        cancelButton: 'btn btn-secondary', // Style the cancel button
      },
    });
    // this.loadEditForm(employee);
  }
  generateEditFormHtml(employee: any): string {
    // Customize this to match your employee object structure
    return `
      <form style="max-width: 400px; margin: 20px auto; padding: 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 5px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);">
      <label for="firstName" style="display: block; margin-bottom: 8px; color: #333; font-weight: bold;">First Name:</label>
      <input type="text" id="firstName" value="${employee.firstName}" style="width: 100%; padding: 10px; margin-bottom: 16px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; font-size: 14px;">

      <label for="lastName" style="display: block; margin-bottom: 8px; color: #333; font-weight: bold;">Last Name:</label>
      <input type="text" id="lastName" value="${employee.lastName}" style="width: 100%; padding: 10px; margin-bottom: 16px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; font-size: 14px;">

      <label for="email" style="display: block; margin-bottom: 8px; color: #333; font-weight: bold;">Email:</label>
      <input type="email" id="email" value="${employee.email}" style="width: 100%; padding: 10px; margin-bottom: 16px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; font-size: 14px;">

      <label for="phoneNumber" style="display: block; margin-bottom: 8px; color: #333; font-weight: bold;">Phone:</label>
      <input type="tel" id="phoneNumber" value="${employee.phoneNumber}" style="width: 100%; padding: 10px; margin-bottom: 16px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; font-size: 14px;">

      <button class="btn btn-success" type="submit" style="background-color: #28a745; color: #fff; padding: 12px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px;">Save changes</button>
    </form>
    `;
  }

  editEmployee(EmplId: string,employee: any) {
    this.deptService.editEmployee(this.adminId, this.deptId, EmplId, employee).subscribe(
      (response) => {
        console.log('Employee info updated successfully', response);
      },
      (error) => {
        console.log('Error updating employee info',error);
      }
    )
  }

  deleteEmployee(EmplId: string): void {
    this.deptService
      .deleteEmployee(this.adminId, this.deptId, EmplId)
      .subscribe(
        (response) => {
          console.log('Employee deleted successfully', response);
          alert('Employee deleted successfully');
        },
        (error) => {
          console.error('Error deleting employee:', error);
        }
      );
  }
}