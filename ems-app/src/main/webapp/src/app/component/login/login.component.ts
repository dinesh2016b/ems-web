import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormControl } from '@angular/forms';
import { Constants } from 'src/app/model/constants';
import { AuthenticateService } from 'src/app/service/authenticate.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  TOKEN_KEY = 'auth-token';
  USER_KEY = 'auth-user';

  loginForm = new UntypedFormGroup({
    userName: new UntypedFormControl(''),
    password: new UntypedFormControl('')
  });

  constructor(private authenticateService: AuthenticateService) { }

  ngOnInit() {
    window.sessionStorage.removeItem(this.TOKEN_KEY);
  }

  public onSubmit() {
    let jwtToken = window.sessionStorage.getItem(this.TOKEN_KEY);
    if (jwtToken == null || jwtToken === '' || jwtToken != undefined) {
      this.authenticateService.authenticate().subscribe(data => {
        jwtToken = "Bearer " + data.jwt;
        window.sessionStorage.setItem(this.TOKEN_KEY, jwtToken);
        console.log('JWT Token : ' + jwtToken)
      })
    }
  }
}