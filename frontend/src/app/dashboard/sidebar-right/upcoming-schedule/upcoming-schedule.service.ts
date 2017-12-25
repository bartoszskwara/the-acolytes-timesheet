import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import "rxjs/add/observable/throw";
import { environment } from "../../../../environments/environment";
import { Http } from "@angular/http";

@Injectable()
export class UpcomingScheduleService {

  private serverUrl = `${environment.server.url}`;

  constructor(private http: Http) { }

  getUpcomingSchedule(userId: number): Observable<any> {
    return this.http.get(this.serverUrl + 'schedule/upcoming/' + userId)
      .map(res => res.json());
  }

}
