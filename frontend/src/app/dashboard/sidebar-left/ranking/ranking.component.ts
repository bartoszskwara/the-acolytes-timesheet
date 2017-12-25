import { Component, OnInit } from '@angular/core';
import { RankingService } from './ranking.service';
import { User } from '../../../model/user/User';
import { Points } from '../../../model/points/Points';

@Component({
  selector: 'ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.scss']
})
export class RankingComponent implements OnInit {

  private loading: boolean = false;
  private ranking: User[] = [];
  private usersPerPage: number = 3;
  constructor(private rankingService: RankingService) { }

  ngOnInit() {
    this.getRanking();
  }

  getRanking(): void {
    this.loading = true;
    this.rankingService.getRanking(this.usersPerPage)
      .subscribe(
        response => {
          this.ranking = response.content;
        },
        error => {
          console.log(error);
          console.log('error');
        },
        () => {
          this.loading = false;
          console.log('end ranking');
          console.log(this.ranking);
        }
      );
  }

  getTotalPoints(points: Points[]): number {
    let total = 0;
    for(var i = 0; i < points.length; i++){
      total += points[i].activity.value;
    }
    return total;
  }

}
