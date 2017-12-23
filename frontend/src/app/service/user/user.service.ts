import { Injectable } from '@angular/core';
import { User } from '../../model/user/User'
import { Http, RequestOptions, RequestMethod, Request, Headers, ResponseContentType } from "@angular/http";
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import "rxjs/add/observable/throw";
import { environment } from "../../../environments/environment";

@Injectable()
export class UserService {

  private serverUrl = `${environment.server.url}`;

  constructor(private http: Http) { }

  getCurrentUser(): User {
    let user = new User()
    user.setId(2);
    user.setName('Bartosz Skwara');
    user.setActive(true);
    return user;
  }

  getUserAvatar(id: number): Observable<any> {
    return this.http.get(this.serverUrl + '/user/avatar/' + id, this.getBlobHeaders());
  }


  private getBlobHeaders(): RequestOptions {
    var headers = new Headers();
    headers.append('Content-Type', 'image/jpg');
    return new RequestOptions({
      headers: headers,
      responseType: ResponseContentType.Blob
    });
  }

}
