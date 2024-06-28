import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../core/services/user.service';
import { Router } from '@angular/router';

/**
 * HeaderComponent manages the navigation header, updating its state based on the user's authentication status
 * and the current route.
 *
 * @Component Decorator that specifies Angular metadata for the component.
 */
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isAuthPage: boolean = false;
  isUserActiveRoute: boolean = false;
  isMenuOpen: boolean = false;

  constructor(private userService: UserService, private router: Router) {
    // Listen to route changes to determine if the user is on the profile page.
    this.router.events.subscribe(() => {
      this.checkActiveRoute();
    });
   }

  /**
   * Initializes the component by subscribing to the user$ observable from UserService to determine
   * if a user is authenticated, which affects the header display.
   */
  ngOnInit(): void {
    this.userService.user$.subscribe(user => {
      this.isAuthPage = !!user;
    });
  }

  /**
   * Checks if the current route is the user's profile page, which may trigger UI changes.
   */
  checkActiveRoute() {
    this.isUserActiveRoute = this.router.url === '/profile';
  }
   /**
   * Toggles the state of the navigation menu, primarily for mobile views.
   */
  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
}
