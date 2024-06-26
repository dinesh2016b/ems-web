import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
//import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
//import { NgbdModalBasic } from './modal-basic';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule, RoutingComponent } from './app-routing.module';

// Component
import { AppComponent } from './app.component';
import { EmployeeDetailsComponent } from './component/employees/employee-details/employee-details.component';
import { EmployeeListComponent } from './component/employees/employee-list/employee-list.component';
import { DepartmentDetailsComponent } from './component/departments/department-details/department-details.component';
import { SalariesComponent } from './component/salaries/salaries.component';
import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';
import { LoginComponent } from './component/login/login.component';
import { HeaderComponent } from './component/header/header.component';
import { DepartmentEmployeeListComponent } from './component/departments/department-employee-list/department-employee-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SignupComponent } from './component/signup/signup.component';
import { HomeComponent } from './component/home/home.component';
import { FooterComponent } from './component/footer/footer.component';
import { ProfileComponent } from './component/profile/profile.component';
import { httpInterceptorProviders } from './service/httpRequestInterceptor';

@NgModule({
    declarations: [
        AppComponent,
        EmployeeDetailsComponent,
        RoutingComponent,
        PageNotFoundComponent,
        DepartmentDetailsComponent,
        EmployeeListComponent,
        SalariesComponent,
        EmployeeDetailsComponent,
        LoginComponent,
        DepartmentEmployeeListComponent,
        HeaderComponent,
        SignupComponent,
        HomeComponent,
        FooterComponent,
        ProfileComponent,
        //NgbdModalBasic,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatDialogModule,
        MatInputModule,
        MatButtonModule,
        MatCardModule,
        MatFormFieldModule,
        //NgbModule,
        FormsModule,
        NgbModule
    ],
    //providers: [],
    providers: [httpInterceptorProviders],
    bootstrap: [AppComponent]
})

export class AppModule {

}