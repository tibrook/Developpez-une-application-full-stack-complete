import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guards';
import { UnauthGuard } from './core/guards/unauth.guards';
const routes: Routes = [
  { path: '',    canActivate: [UnauthGuard],    loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule) },
  { path: 'auth',    canActivate: [UnauthGuard],    loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule) },
  { path: 'posts',canActivate: [AuthGuard], loadChildren: () => import('./features/posts/posts.module').then(m => m.PostsModule) },
  { path: 'topics', canActivate: [AuthGuard],loadChildren: () => import('./features/topics/topics.module').then(m => m.TopicsModule) },
  { path: 'profile',canActivate: [AuthGuard],loadChildren: () => import('./features/profile/profile.module').then(m => m.ProfileModule) },
  { path: '**', redirectTo: '' }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
