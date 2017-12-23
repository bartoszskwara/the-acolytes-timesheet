import { Injectable } from '@angular/core';
import { UserService } from '../../service/user/user.service';
import {DomSanitizer} from '@angular/platform-browser';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import "rxjs/add/observable/throw";
import { environment } from "../../../environments/environment";
import { Http } from "@angular/http";

@Injectable()
export class NavbarService {

  constructor(private userService: UserService,
              private sanitizer: DomSanitizer,
              private http: Http) { }

  private serverUrl = `${environment.server.url}`;


  getUserAvatar(userId: number): Observable<any> {
    if(userId == null) {
      return null;
    }

    return this.userService.getUserAvatar(userId)
      .map(res => res.blob())
      .map(blob => {
          var urlCreator = window.URL;
          let url = urlCreator.createObjectURL(blob);
          return this.sanitizer.bypassSecurityTrustStyle('url(' + url + ')');
      })
  }

  getUserPoints(userId: number): Observable<any> {
    if(userId == null) {
      return null;
    }
    return this.http.get(this.serverUrl + 'points/' + userId )
      .map(res => res.json())
  }
}
