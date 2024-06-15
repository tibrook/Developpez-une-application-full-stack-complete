import { Component, OnInit } from '@angular/core';
import { Topic } from 'src/app/interfaces/topic.interface';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {
  topics: Topic[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.topics$.subscribe({
      next: (topics) => {
        if (topics.length > 0) {
          this.topics = topics;
        } 
      }
    });
  }
}
