import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-back-button',
  templateUrl: './back-button.component.html',
  styleUrls: ['./back-button.component.scss']
})
export class BackButtonComponent {
  constructor(private location: Location, private router: Router) { }

  goBack(): void {
    if (this.router.url === '/login') {
      this.router.navigate(['/home']);
    } else {
      this.location.back();
    }
  }
}
