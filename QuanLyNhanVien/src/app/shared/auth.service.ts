import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  token: any;
  role: any;
  constructor(){}
  IsLoggedIn(){
    return localStorage.getItem('token');
  }

  hasRole(){
    if(localStorage.getItem('role') === 'ROLE_ADMIN'){
      return true;
    }
    return false;
  }
}
