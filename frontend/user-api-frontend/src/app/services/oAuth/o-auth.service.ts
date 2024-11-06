import { Injectable } from '@angular/core';
import { environment } from '../../../global.environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OAuthService {

  private accessToken: string | null = null;

  constructor(private http : HttpClient) {}

  handleCallback() : string | null {
    const urlParams = new URLSearchParams(window.location.search);
    const code = urlParams.get('code');
    return code;
  }

  oAuthGoogleGetAccessCode() {

    const params = new URLSearchParams();
    params.set('client_id', environment.oAUTH.GOOGLE.CLIENT_ID);
    params.set('redirect_uri', environment.oAUTH.GOOGLE.REDIRECT_URI);
    params.set('response_type', environment.oAUTH.GOOGLE.RESPONSE_TYPE);
    params.set('scope', environment.oAUTH.GOOGLE.SCOPE);
    params.set('include_granted_scopes', `${environment.oAUTH.GOOGLE.INCLUDE_GRANTED_SCOPES}`);

    window.location.href = `${environment.oAUTH.GOOGLE.oAUTH_URL}?${params.toString()}`;

  }

  oAuthGitHubAccessCode() {

    const params = new URLSearchParams();
    params.set('client_id', environment.oAUTH.GITHUB.CLIENT_ID);
    params.set('redirect_uri', environment.oAUTH.GITHUB.REDIRECT_URI);
    params.set('response_type', environment.oAUTH.GITHUB.RESPONSE_TYPE);
    params.set('scope', environment.oAUTH.GITHUB.SCOPE);
    params.set('include_granted_scopes', `${environment.oAUTH.GITHUB.INCLUDE_GRANTED_SCOPES}`);

    window.location.href = `${environment.oAUTH.GITHUB.oAUTH_URL}?${params.toString()}`;

  }
   
  requestGoogleAccessToken(code: string,): Observable<any> {
    const body = {
      code: code,
    };
    return this.http.post(`${environment.oAUTH.GOOGLE.EXCHANGE_CODE}`, body);
  }

  requestGithubAccessToken(code: string,): Observable<any> {
    const body = {
      code: code,
    };
    return this.http.post(`${environment.oAUTH.GITHUB.EXCHANGE_CODE}`, body);
  }
  
}
