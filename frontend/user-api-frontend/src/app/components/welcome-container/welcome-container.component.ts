import { Component } from '@angular/core';

@Component({
  selector: 'app-welcome-container',
  templateUrl: './welcome-container.component.html',
  styleUrl: './welcome-container.component.css',
  standalone: true
})
export class WelcomeContainerComponent {
  onSubmit() {
    console.log("Iscrizione alla newsletter completata!");
  }
}
