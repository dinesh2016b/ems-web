import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { BackendApiService } from './backend-api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  private authenticateUrl: string = '/apiservice/auth/login';

  private jwtToken: any;
  private authenticateDataSubject: BehaviorSubject<any>;
  private authenticateData: Observable<any>;

  constructor(private backendApiService: BackendApiService) {
    this.authenticateDataSubject = new BehaviorSubject(this.jwtToken);
    this.authenticateData = this.authenticateDataSubject.asObservable();
  }

  authenticate() {
    console.log('----> authenticate()');
    return this.backendApiService.sendPostRequest(this.authenticateUrl, {
      "userName": "admin",
      "password": "admin"
    });
  }
}
