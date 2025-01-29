import { Component, OnInit } from '@angular/core';
import { RequestsService } from 'src/app/service/requests.service';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css']
})
export class AttendanceComponent implements OnInit{
  attandences: any[]=[];
  constructor(
    private requestsService: RequestsService,
  ) { }
  
  ngOnInit(): void {
    this.getAttandences();
  }

  getAttandences() {
    this.requestsService.getAttandences().subscribe(
      (response) => {
        console.log(response);
        this.attandences = response;
      },
      (error) => {
        console.log(error);
      }
    )
  }

}
