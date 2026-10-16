import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeDetailsComponent } from './features/dashboard/employees/employee-details/employee-details.component';
import { EmployeeListComponent } from './features/dashboard/employees/employee-list/employee-list.component';
import { AddEmployeeComponent } from './features/dashboard/employees/add-employee/add-employee.component';
import { DepartmentListComponent } from './features/dashboard/departments/department-list/department-list.component';
import { AddDepartmentComponent } from './features/dashboard/departments/add-department/add-department.component';
import { DepartmentDetailsComponent } from './features/dashboard/departments/department-details/department-details.component';
import { SalariesComponent } from './features/dashboard/salaries/salaries.component';
import { LoginComponent } from './features/auth/login/login.component';
import { PageNotFoundComponent } from './features/dashboard/page-not-found/page-not-found.component';
import { SignupComponent } from './features/auth/signup/signup.component';
import { ProfileComponent } from './features/dashboard/profile/profile.component';
import { HomeComponent } from './core/layout/home/home.component';
import { AuthGuard } from './core/auth/guards/auth.guard';

const routes: Routes = [
    { path: '', component: LoginComponent, pathMatch: 'full' },
    { path: 'ems-login', component: LoginComponent },
    { path: 'ems-signup', component: SignupComponent },
    {
        path: '',
        canActivate: [AuthGuard],
        children: [
            { path: 'ems-home', title: 'Home', component: HomeComponent },
            { path: 'employees', title: 'Employees', component: EmployeeListComponent },
            { path: 'employees/add', title: 'Add Employee', component: AddEmployeeComponent },
            { path: 'ems-employees', title: 'Employees', component: EmployeeListComponent },
            { path: 'ems-employees/:id', title: 'Employee Details', component: EmployeeDetailsComponent },
            { path: 'ems-employeeDetails', title: 'Employee Details', component: EmployeeDetailsComponent },
            { path: 'departments', title: 'Departments', component: DepartmentListComponent },
            { path: 'departments/add', title: 'Add Department', component: AddDepartmentComponent },
            { path: 'ems-departments', title: 'Departments', component: DepartmentListComponent },
            { path: 'ems-departments/:id', title: 'Department Details', component: DepartmentDetailsComponent },
            { path: 'salaries', title: 'Salaries', component: SalariesComponent },
            { path: 'ems-salaries', title: 'Salaries', component: SalariesComponent },
            { path: 'profile', title: 'Profile', component: ProfileComponent },
            { path: 'ems-profile', title: 'Profile', component: ProfileComponent },
            { path: '**', title: 'Page Not Found', component: PageNotFoundComponent }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }

export const RoutingComponent = [LoginComponent, DepartmentListComponent, EmployeeDetailsComponent, EmployeeListComponent, SalariesComponent, PageNotFoundComponent, DepartmentDetailsComponent, HomeComponent];
