import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../types/User.type';
import { Role } from '../types/Role.type';
import { UserServiceCookie } from './user-service-cookie.service';
import { jwtToken } from '../types/JwtToken.type';
import { JwtDecodeService } from './jwt-decode.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  
  user?: User | null;
  role?: Role;
  jwtToken?: jwtToken;
  

  private apiUrl = 'https://localhost:8443/auth'; // Sostituisci con l'URL del tuo backend

  constructor(
    private http: HttpClient, 
    private userServiceCookie : UserServiceCookie,
    private jwtDecodeService : JwtDecodeService
  ) {}

  register(user: User): Observable<User> {
    this.userServiceCookie.setUser(user);    
    return this.postRegister(user);
  }

  login(user: User) : Observable<jwtToken> {
    this.user = this.userServiceCookie.getUser() ?? null;

    if(this.user != null) {
      this.userServiceCookie.setUser(user);
    } 

    return this.postLogin(user);
  }

  postRegister(user: User) : Observable<User> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    this.userServiceCookie.setUser(user);
    
    return this.http.post(`${this.apiUrl}/register/`, user, { headers });
  }

  postLogin(user: User) : Observable<jwtToken> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.apiUrl}/login/`, user, { headers });
  }

}
