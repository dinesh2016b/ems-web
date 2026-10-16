import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { BackendApiService } from '../../../features/dashboard/service/backend-api.service';
import {CookieService} from "ngx-cookie-service";
import { GlobalConstants } from '../../common/global-constants';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {
  public currentUser: Observable<User>;

  private jwtToken: any;
  private authenticateDataSubject: BehaviorSubject<any>;
  private authenticateData: Observable<any>;

  constructor(private backendApiService: BackendApiService,
    private cookieService: CookieService) {
    this.authenticateDataSubject = new BehaviorSubject(this.jwtToken);
    this.authenticateData = this.authenticateDataSubject.asObservable();
  }

  public get currentUserValue(): User
  {
    return JSON.parse(this.cookieService.get("currentUser"));
  }

  isUserLoggedIn(): boolean
  {
    return this.cookieService.get("isLoggedIn") === "true";
  }

  authenticate(userName: string, password: string) {
    return this.backendApiService.sendPostRequest(GlobalConstants.ENDPOINT_AUTH_SIGNIN_URL, {
      userName,
      password
    });
  }
}
