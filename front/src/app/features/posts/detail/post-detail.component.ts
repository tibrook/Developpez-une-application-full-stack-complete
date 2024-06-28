import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from 'src/app/core/services/post.service';
import { CommentService } from 'src/app/core/services/comment.service';
import { Post } from 'src/app/core/interfaces/posts/post.interface';
import { Comment } from 'src/app/core/interfaces/comments/comment.interface';
import { takeUntil } from 'rxjs';
import { Subject } from 'rxjs';
/**
 * Component for displaying the details of a post, including its comments.
 *
 * @Component Decorator that provides Angular metadata for configuring component properties.
 */
@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit, OnDestroy {
  post!: Post;
  comments: Comment[] = [];
  private unsubscribe$ = new Subject<void>();

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private commentService: CommentService,
    private router: Router
  ) { }

  /**
   * Initializes the component by fetching the post details using the post ID from the route.
   */
  ngOnInit(): void {
    const postId = this.route.snapshot.paramMap.get('id');
    if (postId) {
      this.getPost(postId);
    }
  }

  /**
   * Fetches a post by its ID.
   * @param id The ID of the post to fetch.
   */
  getPost(id: string): void {
    this.postService.getPostById(id).pipe(takeUntil(this.unsubscribe$)).subscribe({
      next: (post: Post) => {
        if (post) {
          this.post = post;
        } else {
          this.router.navigate(['/']);
        }
      },
      error: (err) => {
        console.error('Erreur lors de la récupération du post :', err);
        this.router.navigate(['/']);
      }
    });
  }


  /**
   * Adds a new comment to the post.
   * @param content The text content of the new comment.
   */
  addComment(content: string): void {
    const postId = this.route.snapshot.paramMap.get('id');
    if (postId && content.trim()) {
      this.commentService.addComment(postId, content).subscribe({
        next: () => {
          this.getPost(postId);
          const textArea = document.querySelector('textarea');
          if (textArea) {
            textArea.value = '';
          }
        },
        error: (err) => {
          console.error('Erreur lors de l\'ajout du commentaire :', err);
        }
      });
    }
  }
  /**
   * Unsubscribes from all subscriptions when the component is destroyed.
    */
  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}