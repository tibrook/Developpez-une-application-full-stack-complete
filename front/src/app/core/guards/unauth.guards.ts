import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

/**
 * Guard to determine if an unauthenticated user can activate a route.
 * This guard checks if the user is already logged in and prevents access to routes that are intended for unauthenticated users only,
 * such as the login or register pages. If the user is logged in, they are redirected to the 'posts' page.
 *
 * @Injectable Marks the class as one that participates in the dependency injection system.
 * @providedIn 'root' - Specifies that the service should be provided in the root injector, making it available throughout the app.
 */
@Injectable({ providedIn: 'root' })
export class UnauthGuard implements CanActivate {

  constructor(
    private router: Router,
    private authService: AuthService,
  ) { }

  /**
   * Determines if a route can be activated based on the authentication status of the user.
   * Prevents access to certain routes for users who are already logged in.
   *
   * @returns A boolean value indicating whether the route can be activated.
   *          Returns false and redirects to 'posts' if the user is logged in,
   *          otherwise returns true allowing route activation.
   */
  public canActivate(): boolean {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['posts']);
      return false;
    }
    return true;
  }
}
