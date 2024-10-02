import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from "./components/header/header.component";
import { WelcomeContainerComponent } from './components/welcome-container/welcome-container.component';
import { HttpClient, provideHttpClient } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
],
  providers: [provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }
