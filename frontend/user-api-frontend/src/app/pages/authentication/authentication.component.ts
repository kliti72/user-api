import { Component, OnInit } from '@angular/core';
import { FormLoginComponent } from '../../components/form-login/form-login.component';
import { FormRegisterComponent } from '../../components/form-register/form-register.component';
import { RightSidebarComponent } from '../../components/right-sidebar/right-sidebar.component';
import { FrattaleLoadingComponent } from '../../components/frattale-loading/frattale-loading.component';
import { CustomButtonComponent } from '../../components/custom-button/custom-button.component';
import { HeaderComponent } from '../../components/header/header.component';
import { Router } from '@angular/router';
import { ManageServiceCookie } from '../../services/cookie/manage-cookie.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrl: './authentication.component.css',
  standalone: true,
  imports: [FormLoginComponent, FormRegisterComponent, RightSidebarComponent, CustomButtonComponent]

})
export class AuthenticationComponent implements OnInit {
  
  SwitchRegister = false;
  constructor(private router : Router, private manageServiceCookie : ManageServiceCookie){}

  ngOnInit(): void {
    this.router.url === "/login" ? this.SwitchRegister = true : this.SwitchRegister = false;  
  }

  turnLogin() {
    this.SwitchRegister =! this.SwitchRegister;
  }

  btnLogin = {
    label: "Hai già un account? Premi qui per accedere",
    class: "authentication"
  }

  btnRegister = {
    label: "Non hai un account? ",
    class: "authentication"
  }
}
