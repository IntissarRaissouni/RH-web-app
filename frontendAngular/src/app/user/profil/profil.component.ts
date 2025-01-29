import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { UserviceService } from '../service/uservice.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css'],
})
export class ProfilComponent implements OnInit{
  info: any[] = [];
  constructor(
    private authService: AuthService,
    private userService: UserviceService
  ) {}
  ngOnInit(): void {
    this.getinfo();
    console.log(this.info);
  }

  get employeeId(): string | null {
    return this.authService.getUserId();
  }

  getinfo() {
    if (this.employeeId) {
      this.userService.getinfo(this.employeeId).subscribe(
        (response) => {
          this.info = response;
          console.log(this.info);
        },
        (err) => {
          console.log(err);
        }
      );
    }
  }
}
