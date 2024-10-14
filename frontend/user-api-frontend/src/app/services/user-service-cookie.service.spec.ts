import { TestBed } from '@angular/core/testing';

import { UserServiceCookie } from './user-service-cookie.service';

describe('UserServiceService', () => {
  let service: UserServiceCookie;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserServiceCookie);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
