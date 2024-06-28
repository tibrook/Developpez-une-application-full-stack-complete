import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { LoadingService } from '../services/loading.service';
/**
 * An HTTP interceptor that manages the global loading state during the lifecycle of HTTP requests.
 * This interceptor shows a loading indicator when a request starts and hides it when the request completes or fails.
 *
 * @Injectable Allows this class to be injected as a dependency in other components or services.
 */
@Injectable()
export class LoadingInterceptor implements HttpInterceptor {

  constructor(private loadingService: LoadingService) {}

   /**
   * Intercepts HTTP requests to manage the loading indicator.
   * Shows the loading indicator when the request is initiated and hides it upon completion or error.
   *
   * @param request The outgoing HTTP request to intercept.
   * @param next The next interceptor in the HTTP pipeline or the final handler that sends the request.
   * @returns An Observable that emits the HTTP event stream, with the loading state managed around the request handling.
   */
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    this.loadingService.show();
    return next.handle(request).pipe(
      finalize(() => this.loadingService.hide())
    );
  }
}
