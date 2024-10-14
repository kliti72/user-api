import { Injectable } from '@angular/core';
import { User } from '../types/User.type';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceCookie {


  private user: User | null = null;


  private userOsservable = new BehaviorSubject<User | null>(this.user);
  currentUser = this.userOsservable.asObservable();

  setUser(user: User) {
    localStorage.setItem('user', JSON.stringify(user));
    this.userOsservable.next(user);
  }

  changeMessage(user: User){
    this.userOsservable.next(user);
  }

  getUser(): User | null {
    const userData = localStorage.getItem('user');

    if (userData) {
      try {
        this.user = JSON.parse(userData);
      } catch (error) {
        console.error('Errore nel parsing dei dati utente:', error);
      }
    } 
    if(this.user != undefined) {
      this.changeMessage(this.user);
      return this.user;
    } else {
      return null
    }
  }

  clearUser() {
    this.user = {};
    localStorage.removeItem('user'); 
  }
  
  constructor() { }
}
