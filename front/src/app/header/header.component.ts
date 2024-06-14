import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isAuthPage: boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.user$.subscribe(user => {
      this.isAuthPage = !!user;
    });
  }
}
