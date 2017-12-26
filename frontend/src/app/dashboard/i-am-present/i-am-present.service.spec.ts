import { TestBed, inject } from '@angular/core/testing';

import { IAmPresentService } from './i-am-present.service';

describe('IAmPresentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IAmPresentService]
    });
  });

  it('should be created', inject([IAmPresentService], (service: IAmPresentService) => {
    expect(service).toBeTruthy();
  }));
});
