import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Departments } from '../model/departments';
import { BehaviorSubject, Observable } from 'rxjs';
import { BackendApiService } from './backend-api.service';
import { GlobalConstants } from '../common/global-constants';

@Injectable({
    providedIn: 'root'
})
export class DepartmentService {

    //private departmentUrl: string = 'http://localhost:8080/departments';
    private employeeDataSubject: BehaviorSubject<Departments[]>;
    private employeeData: Observable<Departments[]>;
    private employees: Departments[];

    constructor(private backendAPISerivce: BackendApiService) {
        this.employeeDataSubject = new BehaviorSubject(this.employees);
        this.employeeData = this.employeeDataSubject.asObservable();
    }

    public findById(departmentsId) {
        console.log(`${ GlobalConstants.ENDPOINT_DEPARTMENT_URL + 'departments'}/${departmentsId}`);
        return this.backendAPISerivce.sendGetAPIRequest(`${ GlobalConstants.ENDPOINT_DEPARTMENT_URL }/${departmentsId}`);
        //return this.http.get<Departments>((`${this.departmentUrl }/${departmentsId}`));
        // return this.http.get<Departments>((`${this.departmentUrl }/${departmentsId}`),{ headers: { authorization: this.createBasicAuthToken("dinesh", "dinesh") }});
    }

    public findAll(jwtToken: String): Observable<Departments[]> {
        console.log('----> departments');
        return this.backendAPISerivce.sendPostRequest(GlobalConstants.ENDPOINT_DEPARTMENT_URL + '/pageNo/0/size/10', { jwtToken });
        //return this.http.get<Departments[]>((this.departmentUrl + '/pageNo/0/size/10'), { headers: { authorization: this.createBasicAuthToken("dinesh", "dinesh") }});
    }

    public save(department: Departments) {
        console.log('----> departments :' + department);
        return this.backendAPISerivce.sendPostRequest(GlobalConstants.ENDPOINT_DEPARTMENT_URL, department);
    }

    createBasicAuthToken(username: String, password: String) {
        return 'Basic ' + window.btoa(username + ":" + password)
    }
}
