import { Component, OnInit } from '@angular/core';
import { ManageServiceCookie } from '../../services/cookie/manage-cookie.service';
import { Router } from '@angular/router';
import { OAuthService } from '../../services/oAuth/o-auth.service';

@Component({
  selector: 'app-callback',
  templateUrl: './callback.component.html',
  styleUrl: './callback.component.css',
  standalone: true,
})
export class CallbackComponent implements OnInit {

  constructor(private router : Router, private manageServiceCookie : ManageServiceCookie, private oAuthService : OAuthService) {}

  ngOnInit(): void {
    const code = this.oAuthService.handleCallback();
    
    if(code === null) {
      this.router.navigate(['/login']);
    } else {
      this.manageServiceCookie.setAuthorizationCode(code)
      
      console.log("Token access" + this.oAuthService.requestGoogleAccessToken(code).subscribe({
        next: (body) => {
          console.log(body);
        },
        error: (error: any) => {
          console.log(error);
        },
      }));
      this.router.navigate(['/profile']);
    }
    
  }
}
