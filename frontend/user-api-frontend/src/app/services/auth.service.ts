import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../types/User.type';
import { Role } from '../types/Role.type';
import { UserServiceService } from './user-service.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  
  user?: User;
  role?: Role;
  

  private apiUrl = 'https://localhost:8443/auth'; // Sostituisci con l'URL del tuo backend

  constructor(private http: HttpClient, private userService : UserServiceService) {}


  register(user: User): Observable<User> {
    console.log("Richiesta http post iniziata!", JSON.stringify(user));
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    this.userService.setUser(user);
    return this.http.post(`${this.apiUrl}/register/`, user, { headers });
  }

  login(user: User): Observable<any> {
    const body = { 
      email: user.email, 
      password: user.password 
    };
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(`${this.apiUrl}/login/`, body, { headers });
  }

  

}
