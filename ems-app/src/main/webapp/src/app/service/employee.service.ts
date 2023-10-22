import { Injectable } from '@angular/core';
import { Employee } from '../model/employee';
import { BehaviorSubject, Observable } from 'rxjs';
import { BackendApiService } from './backend-api.service';
import { GlobalConstants } from '../common/global-constants';

@Injectable({ providedIn: 'root' })
export class EmployeeService {

    //private employeeUrl: string = 'http://localhost:8080/employees';
    private employeeDataSubject: BehaviorSubject<Employee[]>;
    private employeeData: Observable<Employee[]>;
    private employees: Employee[];

    constructor(private backendAPISerivce: BackendApiService) {
        this.employeeDataSubject = new BehaviorSubject(this.employees);
        this.employeeData = this.employeeDataSubject.asObservable();
    }

    public findById(employeesId) {
        console.log('----> employeesId :' + employeesId);
        console.log(`${ GlobalConstants.ENDPOINT_EMPLOYEE_URL }/${employeesId}`);

        return this.backendAPISerivce.sendGetAPIRequest(GlobalConstants.ENDPOINT_EMPLOYEE_URL+'/id');

        //return this.http.get<Employee[]>(`${this.employeeUrl}/${employeesId}`);
        //return this.http.get<Employee[]>((`${this.employeeUrl}/${employeesId}`),{ headers: { authorization: this.createBasicAuthToken("dinesh", "dinesh") }});
    }

    public findAll(jwtToken: String, employeeRequest: Employee) {
        return this.backendAPISerivce.sendPostRequest(GlobalConstants.ENDPOINT_EMPLOYEE_URL+'/all', { jwtToken, employeeRequest});

        //return this.http.get<Employee[]>((this.employeeUrl + '/pageNo/0/size/10'));
        //return this.http.get<Employee[]>((this.employeeUrl + '/pageNo/0/size/10'), { headers: { authorization: this.createBasicAuthToken("dinesh", "dinesh") }});
    }

    public save(employee: Employee) {
        return this.backendAPISerivce.sendPostRequest(GlobalConstants.ENDPOINT_EMPLOYEE_URL, employee);

        //return this.http.post<Employee>(this.employeeUrl, employee);
    }

    createBasicAuthToken(username: String, password: String) {
        return 'Basic ' + window.btoa(username + ":" + password)
    }
}