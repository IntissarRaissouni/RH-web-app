import { Component, OnInit } from '@angular/core';
import { RequestsService } from 'src/app/service/requests.service';

@Component({
  selector: 'app-resignations',
  templateUrl: './resignations.component.html',
  styleUrls: ['./resignations.component.css'],
})
export class ResignationsComponent implements OnInit {
  requests: any[] = [];
  approvedEmployees: Set<string> = new Set();

  constructor(private requestService: RequestsService) {}

  ngOnInit(): void {
    this.resigns();
  }
  resigns() {
    this.requestService.getResignations().subscribe(
      (response) => {
        console.log(response);
        this.requests = response;
        console.log(this.requests);
      },
      (error) => {
        console.log(error);
      }
    );
  }
  approveRequest(requestId: string): void {
    this.requestService.approve(requestId).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  rejectRequest(requestId: string): void {
    this.requestService.reject(requestId).subscribe(
      (response) => {
        console.log(response);
        
      },
      (error) => {
        console.log(error);
      }
    )
  }
}
