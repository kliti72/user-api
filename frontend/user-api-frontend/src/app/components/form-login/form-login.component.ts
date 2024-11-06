import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { User } from '../../types/User.type';
import { AuthService } from '../../services/auth/auth.service';
import { ManageServiceCookie } from '../../services/cookie/manage-cookie.service';
import { Router } from '@angular/router';
import { environment } from '../../../global.environment';
import { OAuthService } from '../../services/oAuth/o-auth.service';
@Component({
  selector: 'app-form-login',
  templateUrl: './form-login.component.html',
  styleUrl: './form-login.component.css',
  standalone: true,
  imports: [ReactiveFormsModule]
})
export class FormLoginComponent {

  loginForm: FormGroup;
  user?: User | null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private oAuthService : OAuthService,
    private router: Router,) 
    {
      this.loginForm = this.fb.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required]]
      });
    }

  // Richiesta di login
  submitLogin() {

    this.user = { 
      email: this.loginForm.value.email, 
      password: this.loginForm.value.password 
    } 

    // Verifica e validazione
    if (this.loginForm.valid) 
      {
      this.authService.postLogin(this.user).subscribe
      ({
        next: (user: User ) => {
          this.router.navigate(["/profile"])
        },
        error: (error: any) => {
          console.log(error);
        },
      });
      } 

  }

  oAuthGoogleGetAccessCode() {
    this.oAuthService.oAuthGoogleGetAccessCode();
  }

  oAuthGitHubAccessCode() {
    this.oAuthService.oAuthGitHubAccessCode()
  }

}
