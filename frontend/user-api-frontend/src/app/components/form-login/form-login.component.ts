import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { User } from '../../types/User.type';
import { AuthService } from '../../services/auth.service';
import { UserServiceCookie } from '../../services/user-service-cookie.service';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { JwtDecodeService } from '../../services/jwt-decode.service';
import { jwtToken } from '../../types/JwtToken.type';

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
    private authService : AuthService, 
    private authServiceCookie : UserServiceCookie, 
    private router : Router,
    private jwtDecodeService : JwtDecodeService) 
    {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {

      const { email, password } = this.loginForm.value;

      this.user = {
        email: email,
        password: password,
      }

        this.authService.login(this.user).subscribe({
          next: (user: jwtToken) => {
            this.user = this.jwtDecodeService.createUserFromToken(user);
            if(this.user)
            this.authServiceCookie.setUser(this.user);
            console.log(user);
            this.router.navigate(["/"])
          },
          error: (error: any) => {
            console.log("ERRORE email & poassword!")
            console.log(error);
          }
        });
      

      
    } else {
      console.log("ERRORE sulla validazione del form");
      console.log(this.loginForm?.value)
    }
  }

  // Metodo per login con GitHub
  loginWithGitHub() {
    console.log('Login con GitHub');
    // Esegui qui il login tramite API per GitHub
  }

  // Metodo per login con Microsoft
  loginWithMicrosoft() {
    console.log('Login con Microsoft');
    // Esegui qui il login tramite API per Microsoft
  }
}
