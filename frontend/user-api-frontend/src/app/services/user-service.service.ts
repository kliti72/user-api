import { Injectable } from '@angular/core';
import { User } from '../types/User.type';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  private user: User = {};

  setUser(user: User) {
    this.user = user;
  }

  getUser(): User {
    return this.user;
  }
  
  constructor() { }
}
