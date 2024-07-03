import { Component, OnDestroy, OnInit } from '@angular/core';
import { Topic } from 'src/app/core/interfaces/topics/topic.interface';
import { UserService } from 'src/app/core/services/user.service';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
/**
 * Component for displaying topics that the user has not yet subscribed to.
 *
 * @Component Decorator that defines metadata and the Angular selector for the component.
 */
@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit, OnDestroy {
  public topics: Topic[] = [];
  private unsubscribe$ = new Subject<void>();


  constructor(private userService: UserService) { }

  /**
   * Initializes the component by loading topics upon component startup.
   */
  ngOnInit(): void {
    this.userService.topics$.pipe(takeUntil(this.unsubscribe$)).subscribe({
      next: (topics) => {
        if (topics.length > 0) {
          this.topics = topics.filter(topic => !topic.subscribed);
        }
      }
    });
  }

  /**
   * Reacts to subscription changes to refresh the list of unsubscribed topics.
   */
  onSubscriptionChanged(): void {
    this.loadSubscriptions();
  }

  /**
   * Refreshes the list of available topics by filtering out those that are already subscribed to.
   */
  loadSubscriptions(): void {
    // This method can be optimized to avoid subscribing multiple times to the same observable
    // by leveraging other RxJS operators or by unsubscribing properly.
    this.userService.topics$.subscribe(topic => {
      this.topics = topic.filter(topic => !topic.subscribed);
    });
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
