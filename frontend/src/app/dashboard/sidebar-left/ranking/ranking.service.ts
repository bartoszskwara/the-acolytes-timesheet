import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import "rxjs/add/observable/throw";
import { environment } from "../../../../environments/environment";
import { Http } from "@angular/http";

@Injectable()
export class RankingService {
  private serverUrl = `${environment.server.url}`;

  constructor(private http: Http) { }

  getRanking(size: number): Observable<any> {
    return this.http.get(this.serverUrl + 'user/ranking?size=' + size)
      .map(res => res.json());
  }
}
