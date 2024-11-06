import { TestBed } from '@angular/core/testing';
import { ManageServiceCookie } from '../cookie/manage-cookie.service';

describe('UserServiceService', () => {
  let service: ManageServiceCookie;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ManageServiceCookie);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
