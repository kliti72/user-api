import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { FormLoginComponent } from '../../components/form-login/form-login.component';
import { FormRegisterComponent } from '../../components/form-register/form-register.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  standalone: true,
  imports: [HeaderComponent, FormLoginComponent, FormRegisterComponent]
})
export class HomeComponent {

}
