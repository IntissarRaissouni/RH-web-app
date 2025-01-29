import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { UserviceService } from '../service/uservice.service';

@Component({
  selector: 'app-user-claims',
  templateUrl: './user-claims.component.html',
  styleUrls: ['./user-claims.component.css'],
})
export class UserClaimsComponent implements OnInit {
  claimsForm!: FormGroup;
  employeeId!: string;
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private userService: UserviceService,
  ) {}
  
  ngOnInit(): void {
    this.claimsForm = this.fb.group({
      reclamation: ['', Validators.required],
    });
  
    this.employeeId = this.authService.getUserId()!;
  }

  submit() {
    this.userService.submitClaim(this.employeeId, this.claimsForm.value).subscribe(
      (response) => {
        console.log("Claim submitted successfully:", response);
        alert('Claim submitted successfully');
        this.claimsForm.reset();
      },
      (error) => {
        console.log('Error adding claim:', error);
    })
  }
}
