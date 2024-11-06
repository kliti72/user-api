import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../types/User.type';
import { ManageServiceCookie } from '../../services/cookie/manage-cookie.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent implements OnInit {
  isAuthenticated = false;
  dropdownVisible = false;

  constructor(
      private router: Router, 
      private manageServiceCookie : ManageServiceCookie, 
      private cdRef: ChangeDetectorRef
    ) {}

  profile?: User | null;

  ngOnInit(): void {
    this.manageServiceCookie.currentUser.subscribe(user => {
        if(user != null) {
          this.isAuthenticated = true;
        }
      });
  }

  updateAuthState() {
    this.isAuthenticated = this.profile?.name != null;
  }

  toggleDropdown() {
    this.dropdownVisible = !this.dropdownVisible;
  }

  logout() {
    this.router.navigate(['/'])
    this.isAuthenticated = false;
    this.dropdownVisible = false;
  }

  login() {
    this.updateAuthState();
    this.cdRef.detectChanges(); 
  }

  // Navigator function
  navigateToProfile() {
    this.dropdownVisible = false; 
    this.router.navigate(['/profile']);
  }

  navigateToLogin() {
    this.router.navigate(['/login'])
  }

}
