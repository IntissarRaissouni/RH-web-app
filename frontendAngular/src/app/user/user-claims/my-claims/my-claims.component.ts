import { Component, OnInit } from '@angular/core';
import { UserviceService } from '../../service/uservice.service';
import { AuthService } from 'src/app/service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-my-claims',
  templateUrl: './my-claims.component.html',
  styleUrls: ['./my-claims.component.css']
})
export class MyClaimsComponent implements OnInit{
  claims: any[] = [];
  employeeId!: string;
  
  constructor(
    private userService: UserviceService,
    private authService: AuthService
  ){}
  ngOnInit(): void {
    this.employeeId = this.authService.getUserId()!;
    this.getClaims();
  }

  getClaims() {
    this.userService.getClaims(this.employeeId).subscribe(
      (response) => {
        console.log(response);
        this.claims = response;
      },
      (error) => {
        console.log(error);
      }
    )
  }
  delete(claimId: string) {
    this.userService.deleteClaim(this.employeeId,claimId).subscribe(
      (response) => {
        console.log(response);
        Swal.fire({
          icon: 'success',
          title: 'Claim deleted successfully',
          showConfirmButton: false,
          timer: 1500
        });
        this.getClaims();
      },
      (error) => {
        console.log(error);
        Swal.fire({
          icon: 'error',
          title: 'Error deleting claim',
          showConfirmButton: false,
          timer: 1500
        });
      }
    )
  }

}
