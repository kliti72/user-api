import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'https://localhost:8080/auth';

  constructor(private http: HttpClient) {}

  register(email: string, confirmEmail: string, password: string): Observable<any> {
    const body = { email, confirmEmail, password };
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(`${this.apiUrl}/register`, body, { headers });
  }
}
