import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'https://localhost:8080/auth'; // Sostituisci con l'URL del tuo backend

  constructor(private http: HttpClient) {}


  register(email: string, password: string): Observable<any> {
    const body = { email, password };
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(`${this.apiUrl}/register`, body, { headers });
  }


  private data: string[] = ["Exa"];
  getData(): string[] {
    return this.data;
  }
  addData(newData: string) {
    this.data.push(newData);
  }
}
