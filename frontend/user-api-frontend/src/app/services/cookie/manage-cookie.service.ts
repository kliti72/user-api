import { Injectable } from '@angular/core';
import { User } from '../../types/User.type';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ManageServiceCookie {

  private user!: User | null;

  private userOsservable = new BehaviorSubject<User | null>(this.user);
  currentUser = this.userOsservable.asObservable();

  setAuthorizationCode(code: string) {
    localStorage.setItem('code', JSON.stringify(code));
  }
  
}
