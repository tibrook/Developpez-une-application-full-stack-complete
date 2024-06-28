import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

/**
 * Component to provide a back navigation button.
 * This component determines the navigation behavior based on the current URL,
 * ensuring users are taken back to a sensible location within the application.
 *
 * @Component Decorator that specifies Angular metadata for the component.
 */
@Component({
  selector: 'app-back-button',
  templateUrl: './back-button.component.html',
  styleUrls: ['./back-button.component.scss']
})
export class BackButtonComponent implements OnInit {
  isAuthPage: boolean = false;

  constructor(private location: Location, private router: Router) {}

   /**
   * Initializes component by determining if the current page is related to authentication.
   */
  ngOnInit(): void {
    this.isAuthPage = ['login', 'register'].includes(this.router.url);
  }

  /**
   * Navigates back to the previous page or to a default route based on the current URL.
   * This method handles special cases for 'login' and 'register' routes to navigate back to the home page.
   */
  goBack(): void {
    if (this.router.url === '/login' || this.router.url === '/register') {
      this.router.navigate(['']);
    } else {
      this.location.back();
    }
  }
}
