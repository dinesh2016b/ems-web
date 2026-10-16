import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule, RoutingComponent } from './app-routing.module';

// Component
import { AppComponent } from './app.component';
import { EmployeeDetailsComponent } from './features/dashboard/employees/employee-details/employee-details.component';
import { EmployeeListComponent } from './features/dashboard/employees/employee-list/employee-list.component';
import { AddEmployeeComponent } from './features/dashboard/employees/add-employee/add-employee.component';
import { DepartmentDetailsComponent } from './features/dashboard/departments/department-details/department-details.component';
import { AddDepartmentComponent } from './features/dashboard/departments/add-department/add-department.component';
import { SalariesComponent } from './features/dashboard/salaries/salaries.component';
import { PageNotFoundComponent } from './features/dashboard/page-not-found/page-not-found.component';
import { LoginComponent } from './features/auth/login/login.component';
import { HeaderComponent } from './core/layout/header/header.component';
import { DepartmentEmployeeListComponent } from './features/dashboard/departments/department-employee-list/department-employee-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SignupComponent } from './features/auth/signup/signup.component';
import { HomeComponent } from './core/layout/home/home.component';
import { FooterComponent } from './core/layout/footer/footer.component';
import { ProfileComponent } from './features/dashboard/profile/profile.component';
import { httpInterceptorProviders } from './core/auth/interceptors/httpRequestInterceptor';

@NgModule({
    declarations: [
        AppComponent,
        EmployeeDetailsComponent,
        AddEmployeeComponent,
        RoutingComponent,
        PageNotFoundComponent,
        DepartmentDetailsComponent,
        AddDepartmentComponent,
        EmployeeListComponent,
        SalariesComponent,
        EmployeeDetailsComponent,
        LoginComponent,
        DepartmentEmployeeListComponent,
        HeaderComponent,
        SignupComponent,
        HomeComponent,
        FooterComponent,
        ProfileComponent
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
        FormsModule,
        ReactiveFormsModule,
        NgbModule
    ],
    //providers: [],
    providers: [httpInterceptorProviders],
    bootstrap: [AppComponent]
})

export class AppModule {

}
