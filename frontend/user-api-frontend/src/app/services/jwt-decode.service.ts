import { Injectable } from '@angular/core';
import { User } from '../types/User.type';
import { jwtDecode } from 'jwt-decode';
import { jwtToken } from '../types/JwtToken.type';
import { useAnimation } from '@angular/animations';

@Injectable({
  providedIn: 'root'
})
export class JwtDecodeService {

  User? : User;

  constructor() { }

  public createUserFromToken(token: jwtToken): User | null {

    console.log("Creazione user by token in corso...");
    if(token.token) {

    try {
      const jwtToken = token.token.slice(7);
      const decodedToken: any = jwtDecode(jwtToken);
      
      const user: User = {
        id: decodedToken.id,
        name: decodedToken.name,
        surname: decodedToken.surname,
        email: decodedToken.email,
        registerDate: decodedToken.registerDate,
        lastAccess: decodedToken.lastAccess,
        roleId: decodedToken.roleId,
      }

      console.log("User creato:", user);
      return user;
    } catch(error) {
      console.error("Faild to decode token", error);
      this.User;
    }
  }
  return null;
  }

    
}
