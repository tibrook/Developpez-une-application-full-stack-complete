import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from 'src/app/core/services/post.service';
import { CommentService } from 'src/app/core/services/comment.service';
import { Post } from 'src/app/core/interfaces/posts/post.interface';
import { Comment } from 'src/app/core/interfaces/comments/comment.interface';
@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit {
  post!: Post;
  comments: Comment[] = [];

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private commentService: CommentService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const postId = this.route.snapshot.paramMap.get('id');
    if (postId) {
      this.getPost(postId);
    }
  }

  getPost(id: string): void {
    this.postService.getPostById(id).subscribe({
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
}