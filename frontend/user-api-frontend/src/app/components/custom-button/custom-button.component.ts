import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CustomButton } from '../../types/CustomButton.type';

@Component({
  selector: 'app-custom-button',
  templateUrl: './custom-button.component.html',
  styleUrl: './custom-button.component.css',
  standalone: true,
})
export class CustomButtonComponent {
  @Input() buttonConfig!: CustomButton;

}
