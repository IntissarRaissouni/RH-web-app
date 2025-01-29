import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserviceService } from '../../service/uservice.service';
import { AuthService } from 'src/app/service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit{
  checkout!: FormGroup;
  employeeId!: string;
  constructor(
    private fb: FormBuilder,
    private userService: UserviceService,
    private authService: AuthService,
  ) { }
  ngOnInit(): void {
    this.checkout = this.fb.group({
      time: ['', Validators.required],
    });
    this.employeeId = this.authService.getUserId()!;
  }

  checkOut() {
    this.userService.checkOut(this.employeeId, this.checkout.value).subscribe(
      (response) => {
        console.log('Checkout submitted successfully:', response);
        Swal.fire({
          icon: 'success',
          title: 'Checkout submitted successfully',
          showConfirmButton: true,
        });
      },
      (error) => {
        console.log('Error checking out:', error);
        Swal.fire({
          icon: 'error',
          title: 'Ops...',
          text: 'Error checking out!',
          showConfirmButton: true,
        });
      }
    );
  }

}
