import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
})
export class CardComponent {
  @Input() imageUrl!: string;
  @Input() altText!: string;
  @Input() name!: string;
  @Input() color!: string;
  @Input() details!: string;
  @Input() deptId!: string;
  @Output() viewClicked = new EventEmitter<void>();
  @Output() deleteClicked = new EventEmitter<void>();

  view() {
    this.viewClicked.emit();
  }

  delete() {
    this.deleteClicked.emit();
  }
}
