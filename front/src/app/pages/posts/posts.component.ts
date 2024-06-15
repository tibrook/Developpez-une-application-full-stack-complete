import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/interfaces/post.interface';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {
  posts: Post[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.posts$.subscribe({
      next: (posts) => {
        if (posts.length > 0) {
          this.posts = posts;
        } 
      }
    });  }

}
