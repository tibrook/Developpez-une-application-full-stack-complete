import { Component, Input } from '@angular/core';
import { TopicService } from 'src/app/core/services/topic.service';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
@Component({
  selector: 'app-topic-card',
  templateUrl: './topic-card.component.html',
  styleUrls: ['./topic-card.component.scss']
})
export class TopicCardComponent {
  @Input() topic: any;

  constructor(
    private topicService: TopicService,
    private subscriptionService: SubscriptionService
  ) {}

  subscribe(topicId: number): void {
    this.subscriptionService.subscribe(topicId).subscribe({
      next: (response) => {
        this.updateTopicSubscription(topicId, true);
      },
      error: (err) => {
        console.error('Error subscribing to topic', err);
      }
    });
  }

  unsubscribe(topicId: number): void {
    this.subscriptionService.unsubscribe(topicId).subscribe({
      next: (response) => {
        this.updateTopicSubscription(topicId, false);
      },
      error: (err) => {
        console.error('Error unsubscribing from topic', err);
      }
    });
  }
  updateTopicSubscription(topicId: number, subscribed: boolean): void {
    const topic = this.topic;
    if (topic) {
      topic.subscribed = subscribed;
    }
  }
}
