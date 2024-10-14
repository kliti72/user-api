import { ApplicationRef, Component, OnInit, ViewChild } from '@angular/core';
import { FaqCardComponent } from '../../components/faq-card/faq-card.component';
import { User } from '../../types/User.type';
import { UserServiceCookie } from '../../services/user-service-cookie.service';
import { Router } from '@angular/router';
import { HeaderComponent } from '../../components/header/header.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
  standalone: true,
  imports: [FaqCardComponent]
})
export class ProfileComponent implements OnInit{

  @ViewChild(HeaderComponent) headerComponent!: HeaderComponent;

  
  isEditable: boolean = false;
  profile! : User | null;

  constructor(private userCookieService : UserServiceCookie, private router : Router, private appRef: ApplicationRef) {}

  ngOnInit(): void {
    // Iscriviti all'osservabile currentUser per ottenere l'utente aggiornato
    this.userCookieService.currentUser.subscribe(user => {
      this.profile = user;
      if (this.profile === null) {
        this.router.navigate([""]); // Naviga alla home se l'utente è null
      }
      console.log(this.profile);
    });

    // Inizializza profile con l'utente corrente
    this.profile = this.userCookieService.getUser();
    console.log(this.profile);
  }
  
  toggleEdit(): void {
    if(this.profile != null)
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
    if(this.profile != null)
    this.profile.class = "borderTrue"
  }
  
}
