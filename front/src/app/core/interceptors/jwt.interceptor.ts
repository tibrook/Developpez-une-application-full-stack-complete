import { HttpHandler, HttpInterceptor, HttpRequest, HttpEvent, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { Router } from "@angular/router";
import { AuthService } from "../services/auth.service";
import { SessionExpiredModalComponent } from "src/app/shared/components/session-expired-modal/session-expired-modal.component";
import { MatDialog } from "@angular/material/dialog";
@Injectable({ providedIn: 'root' })
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService, private router: Router,private dialog: MatDialog) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401 && error.error.message === 'Bad JWT') {

          this.authService.logout();
          this.dialog.open(SessionExpiredModalComponent).afterClosed().subscribe(() => {
            this.router.navigate(['/login']);
          });        }
        return throwError(error);
      })
    );
  }
}
