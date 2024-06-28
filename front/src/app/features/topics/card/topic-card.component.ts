import { Component, EventEmitter, Input, OnDestroy } from '@angular/core';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
import { Output } from '@angular/core';
import { Topic } from 'src/app/core/interfaces/topics/topic.interface';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
/**
 * Component that represents a single topic card, allowing users to subscribe or unsubscribe from topics.
 *
 * @Component Decorator that defines metadata for the component.
 */
@Component({
  selector: 'app-topic-card',
  templateUrl: './topic-card.component.html',
  styleUrls: ['./topic-card.component.scss']
})
export class TopicCardComponent implements OnDestroy{
  @Input() topic!: Topic; // Input property to receive topic data from the parent component.
  @Output() subscriptionChanged = new EventEmitter<void>(); // Output event to notify the parent component of subscription changes.
  private unsubscribe$ = new Subject<void>();

  constructor(
    private subscriptionService: SubscriptionService
  ) {}

  /**
   * Subscribes to a topic using the topic's ID.
   * @param topicId The ID of the topic to subscribe to.
   */
  subscribe(topicId: number): void {
    this.subscriptionService.subscribe(topicId).pipe(takeUntil(this.unsubscribe$)).subscribe({
      next: () => {
        this.updateTopicSubscription(true);
        this.subscriptionChanged.emit();
      },
      error: (err) => {
        console.error('Error subscribing to topic', err);
      }
    });
  }
  /**
   * Unsubscribes from a topic using the topic's ID.
   * @param topicId The ID of the topic to unsubscribe from.
   */
  unsubscribe(topicId: number): void {
    this.subscriptionService.unsubscribe(topicId).pipe(takeUntil(this.unsubscribe$)).subscribe({
      next: () => {
        this.updateTopicSubscription(false);
        this.subscriptionChanged.emit();
      },
      error: (err) => {
        console.error('Error unsubscribing from topic', err);
      }
    });
  }
   /**
   * Updates the subscription status of the topic.
   * @param subscribed A boolean indicating the new subscription status.
   */
  updateTopicSubscription(subscribed: boolean): void {
    const topic = this.topic;
    if (topic) {
      topic.subscribed = subscribed;
    }
  }

   /**
    * Unsubscribes from the subscription service when the component is destroyed.
   */
  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
