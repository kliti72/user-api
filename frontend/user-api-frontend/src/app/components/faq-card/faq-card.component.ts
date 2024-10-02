import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-faq-card',
  templateUrl: './faq-card.component.html',
  styleUrl: './faq-card.component.css',
  standalone: true
})
export class FaqCardComponent {
  @Input() question: string = '';
  @Input() answer: string = '';

  isOpen: boolean = false;

  toggleAnswer(): void {
    this.isOpen = !this.isOpen;
  }
}
