import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/interfaces/post.interface';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PostService } from 'src/app/services/post.service';
@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent {
  public posts$: Observable<Post[]> = this.postService.getPostsBySubscribedTopics();

  constructor(private postService: PostService, private router:Router) { }

  viewPost(postId: number): void {
    this.router.navigate(['/posts', postId.toString()]);
  }
}
