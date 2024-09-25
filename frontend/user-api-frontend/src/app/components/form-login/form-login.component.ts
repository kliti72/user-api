import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-form-login',
  templateUrl: './form-login.component.html',
  styleUrl: './form-login.component.css',
  standalone: true,
  imports: [ReactiveFormsModule]
})
export class FormLoginComponent {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder) {
    // Inizializza il form con FormBuilder
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  // Metodo per gestire il submit del form di login
  onSubmit() {
    if (this.loginForm.valid) {
      const formData = this.loginForm.value;
      console.log('Form data:', formData);
      // Qui puoi fare la chiamata al backend per l'autenticazione
    } else {
      console.log('Form non valido');
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
