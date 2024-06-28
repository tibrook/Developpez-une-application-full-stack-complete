import { Component , OnInit} from '@angular/core';
import { Router ,NavigationEnd} from '@angular/router';
import { AuthService } from './core/services/auth.service';
import { UserService } from './core/services/user.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  isHomePage: boolean = false;

  constructor(
    private router: Router, 
    private authService: AuthService,
    private userService: UserService)
  {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isHomePage = this.router.url === '/';
      }
    });
  }

  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
      this.userService.loadUserData();
    }
  }
}