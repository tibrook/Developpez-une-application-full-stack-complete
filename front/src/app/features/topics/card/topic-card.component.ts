import { Component, EventEmitter, Input } from '@angular/core';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
import { Output } from '@angular/core';

@Component({
  selector: 'app-topic-card',
  templateUrl: './topic-card.component.html',
  styleUrls: ['./topic-card.component.scss']
})
export class TopicCardComponent {
  @Input() topic: any;
  @Output() subscriptionChanged = new EventEmitter<void>();

  constructor(
    private subscriptionService: SubscriptionService
  ) {}

  subscribe(topicId: number): void {
    this.subscriptionService.subscribe(topicId).subscribe({
      next: () => {
        this.updateTopicSubscription(true);
        this.subscriptionChanged.emit();
      },
      error: (err) => {
        console.error('Error subscribing to topic', err);
      }
    });
  }

  unsubscribe(topicId: number): void {
    this.subscriptionService.unsubscribe(topicId).subscribe({
      next: () => {
        this.updateTopicSubscription(false);
        this.subscriptionChanged.emit();
      },
      error: (err) => {
        console.error('Error unsubscribing from topic', err);
      }
    });
  }
  updateTopicSubscription(subscribed: boolean): void {
    const topic = this.topic;
    if (topic) {
      topic.subscribed = subscribed;
    }
  }
}
