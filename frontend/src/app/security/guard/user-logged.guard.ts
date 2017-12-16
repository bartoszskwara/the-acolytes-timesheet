import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {UserService} from "../../service/user/user.service";
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UserLoggedGuard implements CanActivate {

  constructor(private router: Router, private userService: UserService) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

      if (this.userService.getCurrentUser() != null) {
        return true;
      }
      else {
        console.log('NOT LOGGED');
        this.router.navigate(['/login']);
        return false;
      }
  }
}
