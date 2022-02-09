import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Comments } from '../entity/Comments';
import { Posts } from '../entity/Posts';
import { HttpServiceService } from '../services/http-service.service';

@Component({
  selector: 'app-http-study',
  templateUrl: './http-study.component.html',
  styleUrls: ['./http-study.component.css']
})
export class HttpStudyComponent implements OnInit {
  public posts: FormGroup = new FormGroup({
    title: new FormControl(),
    author: new FormControl()
  });

  public comments: FormGroup = new FormGroup({
    body: new FormControl(),
    postId: new FormControl()
  });

  allPosts = [];

  msg: string = 'msg';

  post: Posts = new Posts('', '');
  comment: Comments = new Comments('', 0);

  constructor(private httpService: HttpServiceService) { }

  ngOnInit(): void {
    this.httpService.getProfile().subscribe((data) => {
      console.log(data);
    });
    this.httpService.getStudents().subscribe((data) => {
      console.log(data);
    });
    this.httpService.getComments().subscribe((data) => {
      console.log(data);
    });
    this.httpService.getPosts().subscribe((data) => {
      this.allPosts = data;
      console.log("all posts:" + JSON.stringify(this.allPosts));
    });
  }

  public allPosts1() {
  }

  public onSubmitPost() {
    this.post.title = this.posts.controls['title'].value;
    this.post.author = this.posts.controls['author'].value;
    // console.log(this.post);
    this.httpService.addPost(this.post).subscribe((data) => {
      console.log(data);
    })
  }

  public onSubmitComment() {
    this.comment.body = this.comments.controls['body'].value;
    this.comment.postId = this.comments.controls['postId'].value;
    this.httpService.addComment(this.comment).subscribe((data) => {
      console.log(data);
    })
  }

  // public getAllProfiles(): Observable<any>{

  // }

}
