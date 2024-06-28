import { Component } from '@angular/core';
import { Post } from 'src/app/core/interfaces/posts/post.interface';
import { Router } from '@angular/router';
import { PostService } from 'src/app/core/services/post.service';

/**
 * Component for displaying a list of posts.
 * Allows users to view, create, and sort posts.
 *
 * @Component Decorator that specifies the Angular metadata for the component.
 */
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

  /**
   * Navigates to the detail view of a specific post.
   * @param postId The ID of the post to view.
   */
  viewPost(postId: number): void {
    this.router.navigate(['/posts/detail', postId.toString()]);
  }

  /**
   * Navigates to the create post view.
   */
  onClickCreatePost(): void {
    this.router.navigate(['/posts/create']);
  }

  /**
   * Fetches posts from the server and sorts them based on the current sorting order.
   */
  loadPosts(): void {
    this.postService.getPostsBySubscribedTopics().subscribe(posts => {
      this.posts = posts.sort((a, b) => this.isAscending ? new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime() :
                                        new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime());
    });
  }

  /**
   * Toggles the sorting order of posts and reloads them.
   */
  toggleSortOrder(): void {
    this.isAscending = !this.isAscending;
    this.loadPosts(); // Refresh the list with the new sort order
  }
}
