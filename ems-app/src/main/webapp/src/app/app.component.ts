import { Component, OnInit, isDevMode } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs/operators';
import { environment } from './../environments/environment';
import { TokenStorageService } from './core/auth/services/token-storage.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
    title = 'Employee Management System';
    showAppChrome = false;

    constructor(private router: Router, private tokenStorage: TokenStorageService) {
        console.log('-------> environment : ' + environment.production); // Logs false for default environment
    }

    ngOnInit() {
        this.updateAppChrome();
        this.router.events.pipe(
            filter(event => event instanceof NavigationEnd)
        ).subscribe(() => {
            this.updateAppChrome();
        });

        if (isDevMode()) { // 👈🏻👈🏻👈🏻
          console.log('Development!');
        } else {
          console.log('Production!');
        }
    }

    private updateAppChrome(): void {
        const isLoginPage = this.router.url === '/' || this.router.url.startsWith('/ems-login');
        this.showAppChrome = !!this.tokenStorage.getToken() && !isLoginPage;
    }
}
