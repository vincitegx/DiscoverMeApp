import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectLinkComponent } from './project-link.component';

describe('ProjectLinkComponent', () => {
  let component: ProjectLinkComponent;
  let fixture: ComponentFixture<ProjectLinkComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProjectLinkComponent]
    });
    fixture = TestBed.createComponent(ProjectLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
