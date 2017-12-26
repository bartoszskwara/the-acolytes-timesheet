import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import "rxjs/add/observable/throw";
import { environment } from "../../../environments/environment";
import { Http, RequestOptions, Headers } from "@angular/http";

@Injectable()
export class IAmPresentService {

  private serverUrl = `${environment.server.url}`;

  constructor(private http: Http) { }

  checkIfThereIsAnyEventNearby(position: Position): Observable<any> {
    return this.http.post(this.serverUrl + 'location', this.convertPositionToValidObject(position), this.getHeaders())
      .map(res => res.json());
  }

  private getHeaders(): RequestOptions {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return new RequestOptions({
      headers: headers
    });
  }

  private convertPositionToValidObject(position: Position): Object {
    return {
      timestamp: position.timestamp,
      coords: {
        accuracy: position.coords.accuracy,
        altitude: position.coords.altitude,
        altitudeAccuracy: position.coords.altitudeAccuracy,
        heading: position.coords.heading,
        latitude: position.coords.latitude,
        longitude: position.coords.longitude,
        speed: position.coords.speed
      }
    }
  }
}
