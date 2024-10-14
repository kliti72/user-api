import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../types/User.type';
import { UserServiceCookie } from '../../services/user-service-cookie.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent implements OnInit {
  isAuthenticated = false;
  dropdownVisible = false;

  constructor(private router: Router, private authCookieService : UserServiceCookie, private cdRef: ChangeDetectorRef) {}

  profile?: User | null;

  ngOnInit(): void {

    this.authCookieService.currentUser.subscribe(user => {
      this.profile = user;
        this.isAuthenticated = true;
      }
    
      
    )

    if(this.authCookieService.getUser() === null){
      this.isAuthenticated = false;
   
    }

  }


  navigateToLogin() {
    this.router.navigate(['/login'])
  }

  toggleDropdown() {
    this.dropdownVisible = !this.dropdownVisible;
  }

  navigateToProfile() {
    this.dropdownVisible = false; 
    this.router.navigate(['/profile']);
  }

  logout() {
    this.authCookieService.clearUser();
    this.router.navigate(['/'])
    this.isAuthenticated = false;
    this.dropdownVisible = false;
  }

  login() {
    this.updateAuthState();
    this.cdRef.detectChanges(); 
  }

  updateAuthState() {
    if(this.profile != null && this.profile != undefined) {
      this.profile = this.authCookieService.getUser();
    }
    this.isAuthenticated = this.profile?.name != null;
  }

}
