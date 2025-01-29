import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { UserviceService } from 'src/app/user/service/uservice.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-my-leaves',
  templateUrl: './my-leaves.component.html',
  styleUrls: ['./my-leaves.component.css'],
})
export class MyLeavesComponent implements OnInit {
  leaves: any[] = [];
  employeeId!: string;

  constructor(
    private authService: AuthService,
    private userService: UserviceService
  ) {}

  ngOnInit(): void {
    this.employeeId = this.authService.getUserId()!;
    this.getLeaves();
  }

  getLeaves() {
    this.userService.getLeaves(this.employeeId).subscribe(
      (response) => {
        console.log(response);
        this.leaves = response;
      },
      (error) => {
        console.log(error);
      }
    );
  }
  delete(leaveId:string) {
    this.userService.deleteLeave(this.employeeId,leaveId).subscribe(
        (response) => {
          console.log(response);
        Swal.fire({
            icon:'success',
            title: 'Leave deleted successfully',
            showConfirmButton: false,
            timer: 1500
          })
          this.getLeaves();
        },
        (error) => {
          console.log(error);
          Swal.fire({
            icon: 'error',
            title: 'Error deleting leave',
            showConfirmButton: false,
            timer: 1500
          })
        }
      )
  }
}
