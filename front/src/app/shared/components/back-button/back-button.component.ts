import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-back-button',
  templateUrl: './back-button.component.html',
  styleUrls: ['./back-button.component.scss']
})
export class BackButtonComponent implements OnInit {
  isAuthPage: boolean = false;

  constructor(private location: Location, private router: Router) {}

  ngOnInit(): void {
    this.isAuthPage = ['login', 'register'].includes(this.router.url);
  }

  goBack(): void {
    if (this.router.url === '/login' || this.router.url === '/register') {
      this.router.navigate(['']);
    } else {
      this.location.back();
    }
  }
}
