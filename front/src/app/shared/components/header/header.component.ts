import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../core/services/user.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isAuthPage: boolean = false;
  isUserActiveRoute: boolean = false;
  constructor(private userService: UserService, private router: Router) {
    this.router.events.subscribe((event) => {
      this.checkActiveRoute();
    });
   }

  ngOnInit(): void {
    this.userService.user$.subscribe(user => {
      this.isAuthPage = !!user;
    });
  }

  checkActiveRoute() {
    this.isUserActiveRoute = this.router.url === '/profile';
  }

}
