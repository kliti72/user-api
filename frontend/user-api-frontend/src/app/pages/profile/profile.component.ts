import { Component, OnInit } from '@angular/core';
import { FaqCardComponent } from '../../components/faq-card/faq-card.component';
import { User } from '../../types/User.type';
import { UserServiceService } from '../../services/user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
  standalone: true,
  imports: [FaqCardComponent]
})
export class ProfileComponent implements OnInit{

  isEditable: boolean = false;
  profile! : User;

  constructor(private userService : UserServiceService, private router : Router) {}

  ngOnInit(): void {

    if(this.userService.getUser() == null) {
        this.router.navigate(['/login'])
      } else {
        this.profile = this.userService.getUser();
        this.profile.class = "profile_field";
        this.router.navigate(['/profile'])
          
      }
  }
  
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
