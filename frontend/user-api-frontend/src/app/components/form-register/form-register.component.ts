import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { User } from '../../types/User.type';
import { Role } from '../../types/Role.type';

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
    private myService: AuthService,
    private router: Router 
  ) {
    this.registrationForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      surname: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      confirmEmail: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {

    if(this.registrationForm?.valid) {

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

        this.myService.register(this.user).subscribe({
          next: (user: User) => {
            console.log("REGISTRATO");
            console.log(user);
            this.router.navigate(['/profile']);
          },
          error: (error: any) => {
            console.log("ERRORE!")
            console.log(error);
          }
        });
      
    } else {
      console.log("ERRORE sulla validazione del form");
      console.log(this.registrationForm?.value)
    }
  }
}
