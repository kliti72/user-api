import { Component } from '@angular/core';
import { WelcomeContainerComponent } from '../../components/welcome-container/welcome-container.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  standalone: true,
  imports: [WelcomeContainerComponent]
 })
export class HomeComponent {

}
