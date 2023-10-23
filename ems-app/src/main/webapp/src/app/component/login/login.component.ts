import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormControl } from '@angular/forms';
import { ActivatedRoute, Router} from '@angular/router';
import { Constants } from 'src/app/model/constants';
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

  loginForm = new UntypedFormGroup({
    userName: new UntypedFormControl(''),
    password: new UntypedFormControl('')
  });

  constructor(private authenticateService: AuthenticateService,
    private route: ActivatedRoute,
    private router: Router,) { }

  ngOnInit() {
    window.sessionStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.TOKEN_KEY);
    window.sessionStorage.removeItem(this.USER_KEY);
    localStorage.removeItem(this.USER_KEY);
  }

  public onSubmit() {
    //let jwtToken = window.sessionStorage.getItem(this.TOKEN_KEY);
    let jwtToken = localStorage.getItem(this.TOKEN_KEY);
    if (jwtToken == null || jwtToken === '' || jwtToken != undefined) {
      this.authenticateService.authenticate().subscribe(data => {
        if(data.jwt != null){
          jwtToken = "Bearer " + data.jwt;
          window.sessionStorage.setItem(this.TOKEN_KEY, jwtToken);
          localStorage.setItem(this.TOKEN_KEY, jwtToken);
          window.sessionStorage.setItem(this.USER_KEY, JSON.stringify(data.user));
          localStorage.setItem(this.USER_KEY, JSON.stringify(data.user));
          
          console.log('JWT Token : ' + jwtToken);
          this.router.navigate(['/ems-employees']);
        }else{
          console.log('JWT Token : Issue occured');
        }
      })
    }
  }
}