import { Component, OnInit } from '@angular/core';
//import { UntypedFormGroup, UntypedFormControl } from '@angular/forms';
import { ActivatedRoute, Router} from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticateService } from 'src/app/service/authenticate.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  TOKEN_KEY = 'auth-token';
  USER_KEY = 'user';
  loginForm: FormGroup;
  loginError = '';
  isSubmitting = false;
 
  constructor(private formBuilder: FormBuilder,
    private authenticateService: AuthenticateService,
    private router: Router) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      userName: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });

  }

  public onSubmit() {
    this.loginError = '';
    if (this.loginForm.invalid || this.isSubmitting) {
      this.loginForm.markAllAsTouched();
      return;
    }

    const { userName, password } = this.loginForm.value;
    this.isSubmitting = true;
    this.authenticateService.authenticate(userName, password).subscribe({
      next: data => {
        if (typeof data.jwt_access_token !== 'string' || data.jwt_access_token.length === 0) {
          this.loginError = 'Sign in failed. Check your username and password and try again.';
          this.isSubmitting = false;
          return;
        }

        const jwtToken = `Bearer ${data.jwt_access_token}`;
        window.sessionStorage.setItem(this.TOKEN_KEY, jwtToken);
        localStorage.setItem(this.TOKEN_KEY, jwtToken);
        window.sessionStorage.setItem(this.USER_KEY, JSON.stringify(data.user ?? null));
        localStorage.setItem(this.USER_KEY, JSON.stringify(data.user ?? null));
        this.router.navigate(['/ems-employees']);
      },
      error: () => {
        this.loginError = 'Unable to sign in. Check your connection and credentials, then try again.';
        this.isSubmitting = false;
      }
    });
  }
}