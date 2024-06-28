import { HttpHandler, HttpInterceptor, HttpRequest, HttpEvent, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { Router } from "@angular/router";
import { AuthService } from "../services/auth.service";
import { SessionExpiredModalComponent } from "src/app/shared/components/session-expired-modal/session-expired-modal.component";
import { MatDialog } from "@angular/material/dialog";

/**
 * An HTTP interceptor that adds JWT (JSON Web Token) authorization header to HTTP requests
 * and handles 401 Unauthorized errors due to expired or invalid JWTs.
 *
 * @Injectable Marks this class as available to be provided and injected as a dependency.
 * @providedIn 'root' - Ensures a single instance of JwtInterceptor is provided across the app.
 */
@Injectable({ providedIn: 'root' })
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService, private router: Router,private dialog: MatDialog) {}

   /**
   * Intercepts HTTP requests to add JWT to the Authorization header if the user is logged in,
   * and handles HTTP errors related to authentication.
   *
   * @param request The outgoing HTTP request to handle.
   * @param next The HTTP request handler delegate.
   * @returns An Observable that emits the HTTP event stream.
   */
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    // Retrieve the user's auth token from local storage
    const token = localStorage.getItem('token');
    // Clone the request to add the authorization header
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }
    // Handle the request and catch potential error responses
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401 && error.error.message === 'Bad JWT') {

          this.authService.logout();
          this.dialog.open(SessionExpiredModalComponent).afterClosed().subscribe(() => {
            this.router.navigate(['/auth/login']);
          });        }
        return throwError(error);
      })
    );
  }
}
