import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(private auth: AuthService){}

  canActivate() {
    var roles = JSON.parse(localStorage.getItem('roles') || '{}');
    for (var i = 0, len = roles.length; i < len; i++) {
      if(roles[i].roleName === 'ROLE_ADMIN'){
        return true;
      }
    }
    return false;
  }
  
}
