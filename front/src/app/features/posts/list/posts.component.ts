import { Component } from '@angular/core';
import { Post } from 'src/app/core/interfaces/posts/post.interface';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PostService } from 'src/app/core/services/post.service';
import { map } from 'rxjs';
@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent {

  public posts: Post[] = [];
  public isAscending = false;

  constructor(private postService: PostService, private router:Router) {
      this.loadPosts();
   }

  viewPost(postId: number): void {
    this.router.navigate(['/posts/detail', postId.toString()]);
  }
  onClickCreatePost(): void {
    this.router.navigate(['/posts/create']);
  }
  loadPosts(): void {
    this.postService.getPostsBySubscribedTopics().subscribe(posts => {
      this.posts = posts.sort((a, b) => this.isAscending ? new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime() :
                                        new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime());
    });
  }

  toggleSortOrder(): void {
    this.isAscending = !this.isAscending;
    this.loadPosts(); // Refresh the list with the new sort order
  }
}
