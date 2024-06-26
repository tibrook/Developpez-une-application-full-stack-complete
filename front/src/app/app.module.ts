import { NgModule, LOCALE_ID } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './features/home/home.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
// import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './shared/components/header/header.component';
import { MatIconModule } from '@angular/material/icon';
import { PostsComponent } from './features/posts/list/posts.component';
import { TopicsComponent } from './features/topics/list/topics.component';
import {  CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { JwtInterceptor } from './core/interceptors/jwt.interceptor';
import { PostDetailComponent } from './features/posts/detail/post-detail.component';
import { BackButtonComponent } from './shared/components/back-button/back-button.component';
import { TopicCardComponent } from './features/topics/card/topic-card.component';
import { ProfileComponent } from './features/profile/profile.component';
import { CreatePostComponent } from './features/posts/create/create-post.component';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import { LoaderComponent } from './shared/components/loader/loader.component';
import { LoadingInterceptor } from './core/interceptors/loading.interceptor';
import { LoadingService } from './core/services/loading.service';
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
