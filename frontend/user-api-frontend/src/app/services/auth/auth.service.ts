import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../types/User.type';
import { Role } from '../../types/Role.type';
import { environment } from '../../../global.environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  
  user?: User | null;
  role?: Role;

  constructor(
    private http: HttpClient, 
  ) {}

  postRegister(user: User) : Observable<User> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });    
    return this.http.post(`${environment.API.AUTHENTICATION.POST_REGISTER}`, user, { headers });
  }

  postLogin(user: User) : Observable<User> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${environment.API.AUTHENTICATION.POST_LOGIN}`, user, { headers });
  }

}
