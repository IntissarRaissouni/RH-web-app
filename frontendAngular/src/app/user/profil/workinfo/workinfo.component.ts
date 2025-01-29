import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-workinfo',
  templateUrl: './workinfo.component.html',
  styleUrls: ['./workinfo.component.css'],
})
export class WorkinfoComponent {
  @Input() employeeInfo: any;
}
