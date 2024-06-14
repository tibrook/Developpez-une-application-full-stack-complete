import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { HttpClientModule } from '@angular/common/http';
// import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { LoginComponent } from './auth/Components/login/login.component';
import { RegisterComponent } from './auth/Components/register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
@NgModule({
  declarations: [AppComponent, HomeComponent, LoginComponent, RegisterComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    HttpClientModule,
    ReactiveFormsModule, 
  ],

  bootstrap: [AppComponent],
})
export class AppModule {}
