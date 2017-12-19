import { Component, OnInit } from '@angular/core';
import { ScheduleService } from './schedule.service'
import { UserService } from '../../service/user/user.service'
import { Schedule } from '../../model/schedule/Schedule'

@Component({
  selector: 'schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})
export class ScheduleComponent implements OnInit {

  private schedules: Schedule[];

  constructor(private scheduleService : ScheduleService,
              private userService : UserService) { }

  ngOnInit() {
    this.getScheduleByUserId(this.userService.getCurrentUser().getId());
  }

  private getScheduleByUserId(userId: number): void {
    this.scheduleService.getScheduleByUserId(userId)
      .subscribe(
        response => {
          console.log(response);
          this.schedules = response.schedule;
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
