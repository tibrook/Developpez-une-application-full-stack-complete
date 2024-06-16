import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'src/app/services/post.service';
import { CommentService } from 'src/app/services/comment.service';
@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit {
  post: any;
  comments: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private commentService: CommentService
  ) { }

  ngOnInit(): void {
    const postId = this.route.snapshot.paramMap.get('id');
    if (postId) {
      this.getPost(postId);
    }
  }

  getPost(id: string): void {
    this.postService.getPostById(id).subscribe((post: any) => {
      this.post = post;
    });
  }

  addComment(): void {
  // TO DO
}
}