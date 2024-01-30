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

    public findById(jwtToken: string, employeesId) {
        console.log('----> employeesId :' + employeesId);
        console.log(`${ GlobalConstants.ENDPOINT_EMPLOYEE_URL }/${employeesId}`);

        return this.backendAPISerivce.sendGetAPIRequest(GlobalConstants.ENDPOINT_EMPLOYEE_URL+'/id',jwtToken);

        //return this.http.get<Employee[]>(`${this.employeeUrl}/${employeesId}`);
        //return this.http.get<Employee[]>((`${this.employeeUrl}/${employeesId}`),{ headers: { authorization: this.createBasicAuthToken("dinesh", "dinesh") }});
    }

    public findAll(jwtToken: string, employee: Employee) {
        return this.backendAPISerivce.sendPostRequest(GlobalConstants.ENDPOINT_EMPLOYEE_URL+'/all', { jwtToken, employee });

        //return this.http.get<Employee[]>((this.employeeUrl + '/pageNo/0/size/10'));
        //return this.http.get<Employee[]>((this.employeeUrl + '/pageNo/0/size/10'), { headers: { authorization: this.createBasicAuthToken("dinesh", "dinesh") }});
    }

    public save(jwtToken: string, employee: Employee) {
        return this.backendAPISerivce.sendPostRequest(GlobalConstants.ENDPOINT_EMPLOYEE_URL, { jwtToken, employee });

        //return this.http.post<Employee>(this.employeeUrl, employee);
    }

    public update(jwtToken: string, employee: Employee) {
        return this.backendAPISerivce.sendPutRequest(GlobalConstants.ENDPOINT_EMPLOYEE_URL, { jwtToken, employee });

        //return this.http.post<Employee>(this.employeeUrl, employee);
    }

    public delete(jwtToken: string, employee: Employee) {
        return this.backendAPISerivce.sendDeleteRequest(GlobalConstants.ENDPOINT_EMPLOYEE_URL, { jwtToken,employee });

        //return this.http.post<Employee>(this.employeeUrl, employee);
    }

    createBasicAuthToken(username: string, password: string) {
        return 'Basic ' + window.btoa(username + ":" + password)
    }
}