import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';

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

  constructor(
    private fb: FormBuilder,
    private myService: AuthService,
    private router: Router 
  ) {
    this.registrationForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      confirmEmail: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if(this.registrationForm?.valid) {
      const { email, confirmEmail, password } = this.registrationForm.value;
      this.myService.register(email, password).subscribe((data : any[]) => {
        next: (result: any) => {
          console.log(result);
        }
        error: (error: any) => {
          console.log(error);
        }
        complete: (complete: any) => {
          console.log(complete);
        }
      })
    }
    console.log(this.myService.getData());
  }

}
