import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

/**
 * Guard to determine if a user can activate a route.
 * This guard checks if the user is logged in before allowing navigation to a route.
 * If the user is not logged in, it redirects them to the login page.
 *
 * @Injectable Marks the class as one that participates in the dependency injection system.
 * @providedIn 'root' - This service is provided in the root injector and should be a singleton.
 */
@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router,
    private authService: AuthService,
  ) { }

   /**
   * Determines if a route can be activated based on the login status of the user.
   *
   * @returns A boolean value, true if the route can be activated, false otherwise.
   *          If false, redirects the user to the login page.
   */
  public canActivate(): boolean {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
