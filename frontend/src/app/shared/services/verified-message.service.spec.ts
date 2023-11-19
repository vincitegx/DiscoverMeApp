import { TestBed } from '@angular/core/testing';

import { VerifiedMessageService } from './verified-message.service';

describe('VerifiedMessageService', () => {
  let service: VerifiedMessageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VerifiedMessageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
