import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import "rxjs/add/observable/throw";
import { Http } from "@angular/http";
import { environment } from "../../../environments/environment";

@Injectable()
export class ScheduleService {

  constructor(private http: Http) { }

  private serverUrl = `${environment.server.url}`;

  getScheduleByUserId(userId: number) : Observable<any> {
    return this.http.get(this.serverUrl + 'schedule/' + userId )
      .map(res => res.json())
  }

}
