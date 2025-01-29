import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { UserviceService } from 'src/app/user/service/uservice.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-my-resigns',
  templateUrl: './my-resigns.component.html',
  styleUrls: ['./my-resigns.component.css']
})
export class MyResignsComponent implements OnInit{
  resigns: any[] = [];
  employeeId!: string;

  constructor(
    private authService: AuthService,
    private userService: UserviceService,
  ) { }
  
  ngOnInit(): void {
    this.employeeId = this.authService.getUserId()!;
    this.getResigns();
  }

  getResigns() {
    this.userService.getResigns(this.employeeId).subscribe(
      (response) => {
        console.log(response);
        this.resigns = response;
      },
      (error) => {
        console.log(error);
      }
    )
  }
  delete(resignId: string) {
    this.userService.deleteResign(this.employeeId, resignId).subscribe(
      (response) => {
          console.log(response);
        Swal.fire({
            icon:'success',
            title: 'Resign deleted successfully',
            showConfirmButton: false,
            timer: 1500
          })
          this.getResigns();
        },
      (error) => {
        console.log(error);
        Swal.fire({
          icon: 'error',
          title: 'An error occurred',
          showConfirmButton: false,
          timer:1500,
        });
      })
  }

}
