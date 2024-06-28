import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostDetailComponent } from './detail/post-detail.component';
import { PostsComponent } from './list/posts.component';
import { CreatePostComponent } from './create/create-post.component';

const routes: Routes = [
  { path: 'detail/:id', component: PostDetailComponent },
  { path: '', component: PostsComponent },
  { path: 'create', component: CreatePostComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostsRoutingModule { }
