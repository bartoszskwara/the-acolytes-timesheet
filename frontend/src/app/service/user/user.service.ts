import { Injectable } from '@angular/core';
import { User } from '../../model/user/User'

@Injectable()
export class UserService {

  constructor() { }

  getCurrentUser(): User {
    return {
      id: 1,
      name: 'Bartosz Skwara'
    }
  }

}
