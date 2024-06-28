import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { PostsRoutingModule } from './posts-routing.module';
import { PostsComponent } from './list/posts.component';
import { PostDetailComponent } from './detail/post-detail.component';
import { CreatePostComponent } from './create/create-post.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { SharedModule } from 'src/app/shared/components/shared.module';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [
    PostsComponent,
    PostDetailComponent,
    CreatePostComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    PostsRoutingModule,MatIconModule,
    SharedModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class PostsModule { }
