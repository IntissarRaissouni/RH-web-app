import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { UserviceService } from '../../service/uservice.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-user-leave',
  templateUrl: './user-leave.component.html',
  styleUrls: ['./user-leave.component.css'],
})
export class UserLeaveComponent implements OnInit {
  leaveinfos!: FormGroup;
  employeeId!: string;
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private userService: UserviceService
  ) {}

  ngOnInit(): void {
    this.leaveinfos = this.fb.group({
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      descrepsion:['']
    });
    this.employeeId = this.authService.getUserId()!;
  }
  askleave() {
    const leaveData = {
      startDate: this.leaveinfos.get('startDate')?.value,
      endDate: this.leaveinfos.get('endDate')?.value,
      descrepsion: this.leaveinfos.get('descrepsion')?.value,
    };
    this.userService.leave(this.employeeId, leaveData).subscribe(
      (response) => {
        console.log('submitted successfully:', response);
        Swal.fire({
          icon: 'success',
          title: 'submitted successfully',
          timer: 1500,
        });
        this.leaveinfos.reset();
      },
      (error) => {
        console.log('Error adding leave:', error);
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Error adding leave!',
          timer: 1500,
        });
        this.leaveinfos.reset();
      }
    );
  }
}
