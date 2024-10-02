import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  isAuthenticated = false;
  userProfileImage = 'path/to/profile-image.jpg'; // Percorso dell'immagine del profilo
  userName = 'Nome Utente'; // Nome dell'utente
  dropdownVisible = false;

  constructor(private router: Router) {}

  navigateToLogin() {
    this.router.navigate(['/login']);
  }

  toggleDropdown() {
    this.dropdownVisible = !this.dropdownVisible;
  }

  navigateToProfile() {
    this.router.navigate(['/profilo']);
    this.dropdownVisible = false; // Chiude il dropdown
  }

  logout() {
    this.isAuthenticated = false;
    this.dropdownVisible = false;
  }

}
