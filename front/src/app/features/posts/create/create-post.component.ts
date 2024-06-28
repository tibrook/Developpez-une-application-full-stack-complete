import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PostService } from 'src/app/core/services/post.service';
import { UserService } from 'src/app/core/services/user.service';
import { Topic } from 'src/app/core/interfaces/topics/topic.interface';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {
  postForm!: FormGroup;
  submitted = false;
  errorMessage: string = '';
  topics: Topic[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private postService: PostService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.postForm = this.formBuilder.group({
      title: ['', [Validators.required]],
      content: ['', [Validators.required]],
      topicId: ['', [Validators.required]]
    });

    // Load subscribed topics
    this.userService.topics$.subscribe({
      next: (topics) => {
        console.log(topics)
        this.topics = topics
        // this.topics = topics.filter((topic) => topic.subscribed);
      },
      error: (err) => {
        console.error('Error loading topics', err);
      }
    });
  }

  get formControls() {
    return this.postForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.postForm.invalid) {
      return;
    }

    const newPost = this.postForm.value;

    this.postService.createPost(newPost).subscribe({
      next: (response) => {
        console.log('Post created successfully', response);
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Error creating post', error);
        this.errorMessage = error.error.message || 'Post creation failed';
      }
    });
  }
}
