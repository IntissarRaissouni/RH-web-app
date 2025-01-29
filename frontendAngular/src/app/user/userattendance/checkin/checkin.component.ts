import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { UserviceService } from '../../service/uservice.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-checkin',
  templateUrl: './checkin.component.html',
  styleUrls: ['./checkin.component.css'],
})
export class CheckinComponent implements OnInit {
  checkin!: FormGroup;
  employeeId!: string;
  constructor(private fb: FormBuilder, private authService: AuthService, private userService: UserviceService) {}

  ngOnInit(): void {
    this.checkin = this.fb.group({
      time: ['', Validators.required],
    });
    this.employeeId = this.authService.getUserId()!;
  }
  checkIn() {
    this.userService.checkIn(this.employeeId, this.checkin.value).subscribe(
      (response) => {
        console.log('Checkin submitted successfully:', response);
        Swal.fire({
          icon: 'success',
          title: 'Checkin submitted successfully',
          showConfirmButton: true,
        });
        this.checkin.reset();
      },
      (error) => {
        console.log('Error adding checkin:', error);
        console.log(this.checkin.value);
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Error adding checkin!',
          showConfirmButton: true,
        })
        this.checkin.reset();
      }
    )
  }
}
