import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-form-register',
  templateUrl: './form-register.component.html',
  styleUrl: './form-register.component.css',
  standalone: true,
  imports: [ReactiveFormsModule]
})
export class FormRegisterComponent implements OnInit{
  registrationForm: FormGroup;
  registrationError: string | null = null; // Per gestire gli errori di registrazione
  registrationSuccess: boolean = false; // Per gestire il successo della registrazione

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router // Se desideri navigare dopo la registrazione
  ) {
    // Inizializza il form
    this.registrationForm = this.fb.group({
      email: ['w', [Validators.required, Validators.email]],
      confirmEmail: ['xxx', [Validators.required, Validators.email]],
      password: ['dwadw', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit(): void {
    // Puoi eseguire altre inizializzazioni qui se necessario
  }

  // Funzione per gestire l'invio del modulo
  onSubmit() {
    if (this.registrationForm.valid) {
      const { email, confirmEmail, password } = this.registrationForm.value;
      this.authService.register(email, confirmEmail, password).subscribe({
        next: (response) => {
          this.registrationSuccess = true; // Imposta il flag di successo
          this.registrationError = null; // Resetta gli errori
          console.log('Registrazione avvenuta con successo!', response);
          // Puoi navigare a un'altra pagina qui, ad esempio:
          this.router.navigate(['/login']); // Naviga alla pagina di login o dove vuoi
        },
        error: (error) => {
          this.registrationError = 'Errore nella registrazione. Riprova.'; // Gestisci l'errore
          this.registrationSuccess = false; // Resetta il flag di successo
          console.error('Errore nella registrazione:', error);
        }
      });
    } else {
      console.log('Il modulo non è valido');
    }
  }
}
