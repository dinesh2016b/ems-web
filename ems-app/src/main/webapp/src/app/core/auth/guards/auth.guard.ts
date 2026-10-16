import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

  constructor(private tokenStorage: TokenStorageService, private router: Router) { }

  canActivate(): boolean | UrlTree {
    return this.tokenStorage.getToken()
      ? true
      : this.router.createUrlTree(['/ems-login']);
  }
}
