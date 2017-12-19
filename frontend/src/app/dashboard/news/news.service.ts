import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import "rxjs/add/observable/throw";
import { Http } from "@angular/http";
import { environment } from "../../../environments/environment";

@Injectable()
export class NewsService {

  constructor(private http: Http) { }

  private serverUrl = `${environment.server.url}`;

  getNews() : Observable<any> {
    return this.http.get(this.serverUrl + 'news/' )
      .map(res => res.json())
  }
}
