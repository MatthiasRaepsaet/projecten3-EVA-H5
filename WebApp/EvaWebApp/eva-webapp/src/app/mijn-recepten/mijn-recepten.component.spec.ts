import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MijnReceptenComponent } from './mijn-recepten.component';

describe('MijnReceptenComponent', () => {
  let component: MijnReceptenComponent;
  let fixture: ComponentFixture<MijnReceptenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MijnReceptenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MijnReceptenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
