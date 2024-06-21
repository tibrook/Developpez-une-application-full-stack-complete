import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
@Injectable({ providedIn: 'root' })
export class UnauthGuard implements CanActivate {

  constructor(
    private router: Router,
    private authService: AuthService,
    private userService : UserService
  ) { }

  public canActivate(): boolean {
    if (this.authService.isLoggedIn()) {
      const token = localStorage.getItem('token');
      // if (token) {
      //   this.userService.setUser({ token });
      // }
      this.router.navigate(['posts']);
      return false;
    }
    return true;
  }
}
