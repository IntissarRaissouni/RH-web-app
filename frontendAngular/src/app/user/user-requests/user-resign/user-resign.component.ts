import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { UserviceService } from '../../service/uservice.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-user-resign',
  templateUrl: './user-resign.component.html',
  styleUrls: ['./user-resign.component.css'],
})
export class UserResignComponent implements OnInit {
  todayDate: string;
  resigning!: FormGroup;
  employeeId!: string;
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private userService: UserviceService
  ) {
    this.todayDate = new Date().toISOString().split('T')[0];
  }
  ngOnInit(): void {
    this.resigning = this.fb.group({
      request_date: [this.todayDate],
      resign_date: ['', Validators.required],
      reason: [''],
    });
    this.employeeId = this.authService.getUserId()!;
  }
  Resign() {
    // Use SweetAlert2 for confirmation
    Swal.fire({
      title: 'Are you sure you want to resign?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, resign!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.userService.Resign(this.employeeId, this.resigning.value).subscribe(
            (response) => {
              console.log('Resignation submitted successfully:', response);
              Swal.fire(
                'Resigned!',
                'Your resignation has been submitted.',
                'success'
              );
            this.resigning.reset();
            },
            (error) => {
              console.log('Error adding resignation:', error);
              Swal.fire(
                'Error!',
                'An error occurred while processing your resignation.',
                'error'
              );
              this.resigning.reset();
            }
          );
      }
    });
  }
}
