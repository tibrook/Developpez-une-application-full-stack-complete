import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopicCardComponent } from './card/topic-card.component';
import { TopicsComponent } from './list/topics.component';
import { TopicsRoutingModule } from './topics-routing.module';
import { SharedModule } from 'src/app/shared/components/shared.module';

@NgModule({
  declarations: [
    TopicsComponent,
    TopicCardComponent
  ],
  imports: [
    CommonModule,
    TopicsRoutingModule,
    SharedModule
  ],
  exports: [
    TopicCardComponent 
  ]
})
export class TopicsModule { }
