import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PostService } from 'src/app/core/services/post.service';
import { UserService } from 'src/app/core/services/user.service';
import { Topic } from 'src/app/core/interfaces/topics/topic.interface';
import { takeUntil } from 'rxjs';
import { Subject } from 'rxjs';
/**
 * Component for creating new posts.
 *
 * @Component Decorator that defines metadata and selector for the component.
 */
@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit, OnDestroy {
  postForm!: FormGroup;
  submitted = false;
  errorMessage: string = '';
  topics: Topic[] = [];
  private unsubscribe$ = new Subject<void>();


  constructor(
    private formBuilder: FormBuilder,
    private postService: PostService,
    private userService: UserService,
    private router: Router
  ) {}

  /**
   * Initializes component, form, and loads necessary data.
   */
  ngOnInit(): void {
    this.postForm = this.formBuilder.group({
      title: ['', [Validators.required]],
      content: ['', [Validators.required]],
      topicId: ['', [Validators.required]]
    });

    // Load subscribed topics
    this.userService.topics$.pipe(takeUntil(this.unsubscribe$)).subscribe({
      next: (topics) => {
        this.topics = topics;
      },
      error: (err) => {
        console.error('Error loading topics', err);
      }
    });
  }
  /**
   * Getter for easy access to form controls within the template.
   */
  get formControls() {
    return this.postForm.controls;
  }

  /**
   * Handles form submission to create a new post.
   */
  onSubmit(): void {
    this.submitted = true;
    if (this.postForm.invalid) {
      return;
    }

    const newPost = this.postForm.value;

    this.postService.createPost(newPost).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Error creating post', error);
        this.errorMessage = error.error.message || 'Post creation failed';
      }
    });
  }

  /**
   * Unsubscribe to avoid memory leaks.
   */
  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
