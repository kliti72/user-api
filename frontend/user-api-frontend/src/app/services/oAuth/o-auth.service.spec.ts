import { TestBed } from '@angular/core/testing';
import { OAuthService } from '../oAuth/o-auth.service';

describe('OAuthService', () => {
  let service: OAuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OAuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
