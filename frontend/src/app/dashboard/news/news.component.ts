import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user/user.service';
import { NewsService } from './news.service';
import { News } from '../../model/news/News'

@Component({
  selector: 'news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  private news: News[];

  constructor(private newsService : NewsService,
              private userService : UserService) { }

  ngOnInit() {
    this.getNews();
  }

  private getNews(): void {
    this.newsService.getNews()
      .subscribe(
        response => {
          console.log(response);
          this.news = response;
        },
        error => {
          console.log(error);
          console.log('error');
        },
        () => {
          console.log('end');
        }
      );
  }
}
