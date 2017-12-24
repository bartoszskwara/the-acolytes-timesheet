import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import "rxjs/add/observable/throw";
import { environment } from "../../../../environments/environment";
import { Http } from "@angular/http";

@Injectable()
export class UpcomingEventService {

  private serverUrl = `${environment.server.url}`;

  constructor(private http: Http) { }

  getUpcomingEvent(): Observable<any> {
    return this.http.get(this.serverUrl + 'event/upcoming')
      .map(res => res.json())
  }

}
