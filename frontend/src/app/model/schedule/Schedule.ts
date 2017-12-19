import {Place} from '../place/Place';
import {Activity} from '../activity/Activity';

export class Schedule {
  id: number;
  place: Place;
  activity: Activity;
  date: Date;
}
