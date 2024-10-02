import { Component } from '@angular/core';
import { FormLoginComponent } from '../../components/form-login/form-login.component';
import { FormRegisterComponent } from '../../components/form-register/form-register.component';
import { RightSidebarComponent } from '../../components/right-sidebar/right-sidebar.component';
import { FrattaleLoadingComponent } from '../../components/frattale-loading/frattale-loading.component';
import { CustomButtonComponent } from '../../components/custom-button/custom-button.component';
import { HeaderComponent } from '../../components/header/header.component';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrl: './authentication.component.css',
  standalone: true,
  imports: [FormLoginComponent, FormRegisterComponent, RightSidebarComponent, FrattaleLoadingComponent, CustomButtonComponent]

})
export class AuthenticationComponent {


  SwitchRegister = false;

  btnLogin = {
    label: "Hai già un account? Premi qui per accedere",
    class: "authentication"
  }

  btnRegister = {
    label: "Non hai un account? ",
    class: "authentication"
  }

  turnLogin() {
    if(this.SwitchRegister){
      this.SwitchRegister = false;
    } else {
      this.SwitchRegister = true;
    }
  }
}
