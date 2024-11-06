import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../types/User.type';
import { OAuthService } from '../../services/oAuth/o-auth.service';

@Component({
  selector: 'app-form-register',
  templateUrl: './form-register.component.html',
  styleUrl: './form-register.component.css',
  standalone: true,
  imports: [ReactiveFormsModule]
})
export class FormRegisterComponent {


  registrationForm: FormGroup;
  registrationError: string | null = null; 
  registrationSuccess: boolean = false; 
  user?: User;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private oAuthService: OAuthService,
    private router: Router,
  ) {
    this.registrationForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      surname: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      confirmEmail: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if(this.registrationForm.valid) {
      const { name, surname, email, confirmEmail, password } = this.registrationForm.value;
      this.user = {
        name: name,
        surname: surname,
        email: email,
        confirmEmail: confirmEmail,
        password: password,
        roleId: 1,
        registerDate: "2023-09-01",
        lastAccess: "2023-09-10",
      }

        this.authService.postRegister(this.user).subscribe({
          next: (user: User) => {
            console.log(user);
            this.router.navigate(['/profile']);
          },
          error: (error: any) => {
            console.log("ERRORE!")
            console.log(error);
          }
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
