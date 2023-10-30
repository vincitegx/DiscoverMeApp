import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitProjectComponent } from './submit-project.component';

describe('SubmitProjectComponent', () => {
  let component: SubmitProjectComponent;
  let fixture: ComponentFixture<SubmitProjectComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SubmitProjectComponent]
    });
    fixture = TestBed.createComponent(SubmitProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
