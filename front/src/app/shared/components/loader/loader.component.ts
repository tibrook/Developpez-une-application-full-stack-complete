import { Component } from '@angular/core';
import { LoadingService } from '../../../core/services/loading.service';

/**
 * LoaderComponent is used to display a loading indicator based on the application-wide loading state.
 *
 * @Component Decorator that defines metadata for the component.
 */
@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.scss']
})
export class LoaderComponent {
  /**
   * Observable that emits loading state changes.
   * Subscribed to in the component's template to show/hide the loading indicator as needed.
   */
  loading$ = this.loadingService.loading$;

  constructor(private loadingService: LoadingService) { }
}
