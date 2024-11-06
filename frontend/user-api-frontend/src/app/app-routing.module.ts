import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AuthenticationComponent } from './pages/authentication/authentication.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { CallbackComponent } from './components/callback/callback.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "login", component: AuthenticationComponent},
  {path: "register", component: AuthenticationComponent},
  {path: "profile", component: ProfileComponent},
  {path: 'callback', component: CallbackComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
