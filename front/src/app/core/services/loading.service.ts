import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

/**
 * Service to control the visibility of a global loading indicator across the application.
 * It uses a BehaviorSubject to maintain the current loading state and provide this state reactively to subscribers.
 *
 * @Injectable Decorator that marks this class as available for dependency injection, with a singleton instance provided in the root injector.
 */
@Injectable({
  providedIn: 'root'
})
export class LoadingService {
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();

  /**
   * Sets the loading state to true, indicating that a loading process has started.
   * This method should be called when an operation that requires a loading indicator begins.
   */
  show() {
    this.loadingSubject.next(true);
  }
  
  /**
   * Sets the loading state to false, indicating that a loading process has ended.
   * This method should be called when an operation that requires a loading indicator completes or fails.
   */
  hide() {
    this.loadingSubject.next(false);
  }
}
