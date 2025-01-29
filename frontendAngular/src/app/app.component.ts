import { Component, OnInit } from '@angular/core';
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit{
  title = 'HRMS-app';
  isLoggedIn : boolean = false;
  userId: string| null=null;
  constructor(private authService: AuthService) { }
  ngOnInit(): void {
    this.isLoggedIn = this.authService.isAuthenticated();
    if (this.isLoggedIn) {
      const userId = this.authService.getUserId();
      if (userId) {
        this.authService.setUserId(userId);
      }
    }
      
  }

  onUserLoggedIn(userId: string) {
    // Update adminId when the login component emits the event
    this.userId = userId;
    this.isLoggedIn = true;
  }
}
