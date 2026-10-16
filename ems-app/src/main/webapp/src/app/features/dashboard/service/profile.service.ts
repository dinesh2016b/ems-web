import { Injectable } from '@angular/core';
import { BackendApiService } from './backend-api.service';
import { GlobalConstants } from '../../../core/common/global-constants';

@Injectable({ providedIn: 'root' })
export class ProfileService {
  constructor(private backendApiService: BackendApiService) { }

  getProfile(jwtToken: string) {
    return this.backendApiService.sendGetAPIRequest(GlobalConstants.ENDPOINT_PROFILE_URL, jwtToken);
  }

  saveProfile(jwtToken: string, user: any) {
    return this.backendApiService.sendPutRequest(GlobalConstants.ENDPOINT_PROFILE_URL, { jwtToken, user });
  }
}
