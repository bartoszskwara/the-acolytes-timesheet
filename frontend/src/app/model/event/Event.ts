export class Event {
  id: number;
  place: {
    id: number;
    name: string;
    coordinates: string
  };
  activity: {
    id: number;
    name: string;
    value: number
  };
  startDate: string;
  endDate: string;
}
