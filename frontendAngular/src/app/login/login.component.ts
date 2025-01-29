import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtService } from 'src/app/service/jwt.service';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  @Output() adminLoggedIn = new EventEmitter<string>();
  showPassword = false;
  loading = false;

  eyeIcon = 'M12 4.5C7 4.5 2.73 7.61 1 12c1.73 4.39 6 7.5 11 7.5s9.27-3.11 11-7.5c-1.73-4.39-6-7.5-11-7.5zM12 17c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5zm0-8c-1.66 0-3 1.34-3 3s1.34 3 3 3 3-1.34 3-3-1.34-3-3-3z';
  eyeOffIcon = 'M12 6.5c1.38 0 2.5 1.12 2.5 2.5 0 .74-.33 1.39-.83 1.85l3.63 3.63c.75-.53 1.42-1.2 1.95-1.95L18 14l2 2-1 1-2-2-3.03-3.03c-.46.5-1.11.83-1.85.83-1.38 0-2.5-1.12-2.5-2.5 0-.74.33-1.39.83-1.85L6.35 3.35 5 2l2 2 1.48 1.48C9.89 3.85 10.94 3.5 12 3.5c4.08 0 7.45 3.05 7.94 7h-2.02c-.46-2.28-2.48-4-4.92-4zM12 17c.28 0 .5-.22.5-.5 0-.16-.08-.3-.19-.39-.24-.2-.57-.2-.8 0-.11.09-.19.23-.19.39 0 .28.22.5.5.5z';

  constructor(
    private service: JwtService,
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      rememberMe: [false]
    });
  }

  ngOnInit(): void {
    const savedEmail = localStorage.getItem('rememberedEmail');
    if (savedEmail) {
      this.loginForm.patchValue({
        email: savedEmail,
        rememberMe: true
      });
    }
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  submitForm(): void {
    if (this.loginForm.invalid) return;

    this.loading = true;
    const formData = this.loginForm.value;

    if (formData.rememberMe) {
      localStorage.setItem('rememberedEmail', formData.email);
    } else {
      localStorage.removeItem('rememberedEmail');
    }

    this.service.login(formData).subscribe({
      next: (response) => this.handleLoginSuccess(response),
      error: (error) => this.handleLoginError(error),
      complete: () => this.loading = false
    });
  }

  private handleLoginSuccess(response: any): void {
    if (response.jwToken) {
      localStorage.setItem('jwt', response.jwToken);
      const decodedToken = this.authService.decodeJwt(response.jwToken);

      if (decodedToken?.userId) {
        this.authService.setUserId(decodedToken.userId);
        
        if (decodedToken.roles?.includes('ROLE_ADMIN')) {
          this.router.navigate([`/admin/${decodedToken.userId}/dashboard`]);
        } else {
          this.router.navigate([`/employee/${decodedToken.userId}/homepage`]);
        }
      }
    }
  }

  private handleLoginError(error: any): void {
    console.error('Login error:', error);
    this.loading = false;
    // Add error handling logic here (e.g., show error message)
  }
}