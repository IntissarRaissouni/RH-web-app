import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { JwtService } from '../service/jwt.service';
import { AuthService } from '../service/auth.service';

@Injectable({
  providedIn: 'root',
})
export class EmployeeRoleGuard implements CanActivate {
  constructor(
    private jwtService: JwtService,
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    const tokenRole = localStorage.getItem('jwt');
    if (tokenRole) {
      const decodedToken = this.authService.decodeJwt(tokenRole);
      if (decodedToken.roles.includes('ROLE_USER')) {
        return true;
      } else {
        alert('You do not have permission to access this page');
        this.authService.logout();
        return false;
      }
    }
    return false;
  }
}
