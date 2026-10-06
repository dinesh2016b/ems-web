import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeDetailsComponent } from './component/employees/employee-details/employee-details.component';
import { EmployeeListComponent } from './component/employees/employee-list/employee-list.component';
import { AddEmployeeComponent } from './component/employees/add-employee/add-employee.component';
import { DepartmentListComponent } from './component/departments/department-list/department-list.component';
import { AddDepartmentComponent } from './component/departments/add-department/add-department.component';
import { DepartmentDetailsComponent } from './component/departments/department-details/department-details.component';
import { SalariesComponent } from './component/salaries/salaries.component';
import { LoginComponent } from './component/login/login.component';
import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';
import { SignupComponent } from './component/signup/signup.component';
import { ProfileComponent } from './component/profile/profile.component';
import { HomeComponent } from './component/home/home.component';

const routes: Routes = [
    { path: '', component: LoginComponent, pathMatch: 'full' },
    { path: 'ems-login', component: LoginComponent },
    { path: 'ems-signup', component: SignupComponent },
    { path: 'ems-home', component: HomeComponent },
    { path: 'employees', component: EmployeeListComponent },
    { path: 'employees/add', component: AddEmployeeComponent },
    { path: 'ems-employees', component: EmployeeListComponent },
    { path: 'ems-employees/:id', component: EmployeeDetailsComponent },
    { path: 'departments', component: DepartmentListComponent },
    { path: 'departments/add', component: AddDepartmentComponent },
    { path: 'ems-departments', component: DepartmentListComponent },
    { path: 'ems-departments/:id', component: DepartmentDetailsComponent },
    { path: 'salaries', component: SalariesComponent },
    { path: 'ems-salaries', component: SalariesComponent },
    { path: 'profile', component: ProfileComponent },
    { path: 'ems-profile', component: ProfileComponent },
    { path: '**', component: PageNotFoundComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }

export const RoutingComponent = [LoginComponent, DepartmentListComponent, EmployeeDetailsComponent, EmployeeListComponent, SalariesComponent, PageNotFoundComponent, DepartmentDetailsComponent, HomeComponent];