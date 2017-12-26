import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IAmPresentComponent } from './i-am-present.component';

describe('IAmPresentComponent', () => {
  let component: IAmPresentComponent;
  let fixture: ComponentFixture<IAmPresentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IAmPresentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IAmPresentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
