import { Injectable } from '@angular/core';
import { User } from '../../model/user/User'

@Injectable()
export class UserService {

  constructor() { }

  getCurrentUser(): User {
    let user = new User()
    user.setId(1);
    user.setName('Bartosz Skwara');
    user.setActive(true);
    return user;
  }

}
