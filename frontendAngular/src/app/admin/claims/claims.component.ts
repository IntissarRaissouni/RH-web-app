import { Component, OnInit } from '@angular/core';
import { RequestsService } from 'src/app/service/requests.service';

@Component({
  selector: 'app-claims',
  templateUrl: './claims.component.html',
  styleUrls: ['./claims.component.css'],
})
export class ClaimsComponent implements OnInit {
  claims: any[] = [];
  constructor(
    private requestService: RequestsService,

  ) { }

  ngOnInit(): void {
    this.getClaims();
  }

  getClaims() {
    this.requestService.getClaims().subscribe(
      (response) => {
        console.log(response);
        this.claims = response;
      },
      (error) => {
        console.log(error);
      }
    )
  }
}
