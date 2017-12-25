import { Points } from '../points/Points';

export class User {
  private id: number;
  private name: string;
  private active: boolean;
  private points: Points[];

  getId(): number {
    return this.id;
  }
  setId(id: number) : void {
    this.id = id;
  }
  getName(): string {
    return this.name;
  }
  setName(name: string) : void {
    this.name = name;
  }
  isActive(): boolean {
    return this.active;
  }
  setActive(active: boolean) : void {
    this.active = active;
  }
  getPoints(): Points[] {
    return this.points;
  }
  setPoints(points: Points[]) : void {
    this.points = points;
  }
}
