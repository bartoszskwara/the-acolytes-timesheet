import { TestBed, inject } from '@angular/core/testing';

import { UpcomingScheduleService } from './upcoming-schedule.service';

describe('UpcomingScheduleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UpcomingScheduleService]
    });
  });

  it('should be created', inject([UpcomingScheduleService], (service: UpcomingScheduleService) => {
    expect(service).toBeTruthy();
  }));
});
