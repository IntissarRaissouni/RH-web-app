import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { DepartmentService } from 'src/app/service/department.service';
import { DeptEmployeesComponent } from '../dept-employees/dept-employees.component';
import { EmployeesDataService } from 'src/app/service/employees-data.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-show-departs',
  templateUrl: './show-departs.component.html',
  styleUrls: ['./show-departs.component.css'],
})
export class ShowDepartsComponent implements OnInit {
  adminId: string = '';
  departments: any[] = [];
  employees: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private deptService: DepartmentService,
    private authService: AuthService,
    private employeesDataService: EmployeesDataService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const adminId = this.authService.getUserId();
      if (adminId) {
        this.adminId = adminId;
      }
      this.loadDepartments();
    });
  }
  loadDepartments() {
    this.deptService.getDepartments(this.adminId).subscribe((data) => {
      this.departments = data;
      console.log(data);
    });
  }

  onViewClicked(deptId: string) {
    this.router.navigate([
      'admin',
      this.adminId,
      'show-departments',
      deptId,
      'employees',
      'getALL',
    ]);
    this.getEmployees(deptId);
  }

  onDeleteClicked(deptId: string) {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger',
      },
      buttonsStyling: false,
    });

    swalWithBootstrapButtons
      .fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'No, cancel!',
        reverseButtons: true,
      })
      .then((result) => {
        if (result.isConfirmed) {
          this.deptService.deleteDepartment(this.adminId, deptId).subscribe(
            (response) => {
              console.log('Department deleted successfully', response);

              // Display success message
              swalWithBootstrapButtons.fire({
                title: 'Deleted!',
                text: 'Department has been deleted.',
                icon: 'success',
              });
            },
            (error) => {              
              console.error('Error deleting department', error);

              // Display error message
              swalWithBootstrapButtons.fire({
                title: 'Error',
                text: 'An error occurred while deleting the department.',
                icon: 'error',
              });
            }
          );
        } else if (result.dismiss === Swal.DismissReason.cancel) {
          // User clicked the "No, cancel!" button or closed the dialog
          swalWithBootstrapButtons.fire({
            title: 'Cancelled',
            icon: 'error',
          });
        }
      });
  }

  getEmployees(deptId: string) {
    this.deptService
      .showdepartEmployees(this.adminId, deptId)
      .subscribe((data) => {
        this.employeesDataService.setEmployees(data);
        this.employeesDataService.setDeptId(deptId);
        console.log(data);
      });
  }
}
