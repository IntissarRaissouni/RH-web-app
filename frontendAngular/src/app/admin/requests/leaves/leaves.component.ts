import { Component, OnInit } from '@angular/core';
import { RequestsService } from 'src/app/service/requests.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-leaves',
  templateUrl: './leaves.component.html',
  styleUrls: ['./leaves.component.css'],
})
export class LeavesComponent implements OnInit {
  leaves: any[] = [];
  constructor(private requestService: RequestsService) {}

  ngOnInit(): void {
    this.Leaves();
  }

  Leaves() {
    this.requestService.getLeaves().subscribe(
      (response) => {
        console.log(response);
        this.leaves = response;
        console.log(this.leaves);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  approveLeave(leaveId: string): void {
    this.requestService.approveLeave(leaveId).subscribe(
      (response) => {
        console.log(response);
        Swal.fire({
          position: 'center',
          icon:'success',
          title: 'Approved!',
          showConfirmButton: false,
          timer: 1500,
        });
        this.Leaves();
      },
      (error) => {
        console.log(error);
        Swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Something went wrong!',
          showConfirmButton: false,
          timer: 1500,
        });
      }
    );
  }

  rejectLeave(leaveId: string): void {
    this.requestService.rejectLeave(leaveId).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
