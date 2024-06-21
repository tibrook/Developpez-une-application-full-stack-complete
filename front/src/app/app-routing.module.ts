import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/Components/login/login.component';
import { RegisterComponent } from './auth/Components/register/register.component';
import { UnauthGuard } from './guards/unauth.guards';
import { AuthGuard } from './guards/auth.guards';
import { PostsComponent } from './pages/posts/posts.component';
import { TopicsComponent } from './pages/topics/topics.component';
import { PostDetailComponent } from './pages/posts/post-detail/post-detail.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { CreatePostComponent } from './pages/posts/create-post/create-post.component';
const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [UnauthGuard] },
  { path: 'login', component: LoginComponent, canActivate: [UnauthGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [UnauthGuard] },
  { path: 'posts', component: PostsComponent, canActivate: [AuthGuard] },
  { path: 'posts/create', component: CreatePostComponent, canActivate: [AuthGuard] },
  { path: 'posts/:id', component: PostDetailComponent, canActivate: [AuthGuard] },
  { path: 'topics', component: TopicsComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '' }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
