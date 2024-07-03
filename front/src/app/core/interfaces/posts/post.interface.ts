import { Comment } from '../comments/comment.interface';
export interface Post {
    id: number;
    title: string;
    content: string;
    author: string; 
    createdAt: Date;
    updatedAt: Date;
    topicName: string;  
    comments: Comment[];  
  }