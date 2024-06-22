import { NgModule, LOCALE_ID } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
// import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { LoginComponent } from './auth/Components/login/login.component';
import { RegisterComponent } from './auth/Components/register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { MatIconModule } from '@angular/material/icon';
import { PostsComponent } from './pages/posts/posts.component';
import { TopicsComponent } from './pages/topics/topics.component';
import {  CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { PostDetailComponent } from './pages/posts/post-detail/post-detail.component';
import { BackButtonComponent } from './utils/back-button/back-button.component';
import { TopicCardComponent } from './pages/topics/topic-card/topic-card.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { CreatePostComponent } from './pages/posts/create-post/create-post.component';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import { LoaderComponent } from './loader/loader.component';
import { LoadingInterceptor } from './interceptors/loading.interceptor';
import { LoadingService } from './services/loading.service';
registerLocaleData(localeFr, 'fr');

@NgModule({
  declarations: [AppComponent, HomeComponent, LoginComponent, RegisterComponent, HeaderComponent, PostsComponent, TopicsComponent, PostDetailComponent, BackButtonComponent, TopicCardComponent, ProfileComponent, CreatePostComponent, LoaderComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    HttpClientModule,
    ReactiveFormsModule, 
    MatIconModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true },
    { provide: LOCALE_ID, useValue: 'fr' },
    LoadingService  
  ],
})
export class AppModule {}
