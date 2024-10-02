import { Component } from '@angular/core';
import { FaqCardComponent } from '../../components/faq-card/faq-card.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
  standalone: true,
  imports: [FaqCardComponent]
})
export class ProfileComponent {
  isEditable: boolean = false;

  profile = {
    name: 'kliti',
    surname: 'elezi',
    email: 'kliti7085@example.com',
    class: "profile_field"
  };

  toggleEdit(): void {
    if(!this.isEditable) {
    this.profile.class = "profile_field borderTrue";
    } else {
    this.profile.class = "profile_field ";
    }
    this.isEditable = !this.isEditable;
  }

  saveProfile(): void {
    console.log('Profilo salvato:', this.profile);
    this.isEditable = false; 
    this.profile.class = "borderTrue"
  }
}
