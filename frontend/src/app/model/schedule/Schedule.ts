import { Event } from '../event/Event';
import { User } from '../user/User';

export class Schedule {
  id: number;
  user: User;
  event: Event;
}
