import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { AuthenticateService } from 'src/app/service/authenticate.service';
import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authenticateService: jasmine.SpyObj<AuthenticateService>;
  let router: jasmine.SpyObj<Router>;

  beforeEach(async(() => {
    authenticateService = jasmine.createSpyObj('AuthenticateService', ['authenticate']);
    router = jasmine.createSpyObj('Router', ['navigate']);
    TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [ReactiveFormsModule],
      providers: [
        { provide: AuthenticateService, useValue: authenticateService },
        { provide: Router, useValue: router }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should reject empty or invalid form values without sending a request', () => {
    component.onSubmit();

    expect(component.loginForm.get('userName')?.touched).toBeTrue();
    expect(component.loginForm.get('password')?.touched).toBeTrue();
    expect(authenticateService.authenticate).not.toHaveBeenCalled();
  });

  it('should submit entered credentials and navigate after successful sign in', () => {
    authenticateService.authenticate.and.returnValue(of({
      jwt_access_token: 'token-value',
      user: { username: 'staff' }
    }));
    component.loginForm.setValue({ userName: 'staff', password: 'secret1' });

    component.onSubmit();

    expect(authenticateService.authenticate).toHaveBeenCalledWith('staff', 'secret1');
    expect(localStorage.getItem('auth-token')).toBe('Bearer token-value');
    expect(router.navigate).toHaveBeenCalledWith(['/ems-employees']);
  });
});
