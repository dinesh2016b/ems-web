import { Component, OnInit, Input } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

import { Employee } from '../../../../core/model/employee';
import { Departments } from '../../../../core/model/departments';
import { EmployeeService } from '../../service/employee.service';
import { DepartmentService } from '../../service/department.service';
import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';
import { TokenStorageService } from '../../../../core/auth/services/token-storage.service';
import { GlobalConstants } from 'src/app/core/common/global-constants';

@Component({
    selector: 'app-employee-list',
    templateUrl: './employee-list.component.html',
    styleUrls: ['./employee-list.component.css']
})

export class EmployeeListComponent implements OnInit {
    TOKEN_KEY = 'auth-token';
    employees: Employee[] = [];
    departments: Departments[] = [];

    constructor(private modalService: NgbModal, private employeeService: EmployeeService,
        private departmentService: DepartmentService,
        private tokenStorageService: TokenStorageService, private router: Router) {
    }

    ngOnInit() {
       console.log('Employee -->> ngOnInit()');
       const department = "All";
       let employeeRequest: Employee ={
           "empNo": "0",
           "firstName": "",
           "lastName": "",
           "birthDate": "",
           "hireDate":"",
           "active": true,
           "departmentsBean":null,
           "salariesBean":null,
           "pageNo": "1",
           "size": "20"
       };
        let jwtToken = localStorage.getItem(this.TOKEN_KEY);
        console.log('JWT Token: ', jwtToken);
        if (jwtToken != null && jwtToken !== '') {
            console.log('Fetching employees from endpoint: ', GlobalConstants.ENDPOINT_EMPLOYEE_URL+'/all');
            this.departments = JSON.parse(localStorage.getItem("departments"));
            if(this.departments == null){
                this.departmentService.findAll(jwtToken).subscribe(data => {
                    console.log('Fetched department: ', data);
                    console.log('Number of department fetched: ', data.length);
                    this.departments = data ?? [];
                    localStorage.setItem("departments", JSON.stringify(this.departments));
                });
            }else {
                 console.log('Already department list fetched ');
            }
            this.employeeService.findAll(jwtToken, employeeRequest,department).subscribe(data => {
                console.log('Fetched employees: ', data);
                console.log('Number of employees fetched: ', data.length);
                this.employees = data ?? [];
                localStorage.setItem("employees", JSON.stringify(this.employees));
            });
        }
    }

    onAddEmployee() {
        // Navigate to Add Employee page
        this.router.navigate(['/employees/add']);
    }

    showEmployeeDetails(employee: Employee) {
        const modalRef = this.modalService.open(EmployeeDetailsComponent, {
            size: 'lg',
            centered: true,
            scrollable: true
        });
        modalRef.componentInstance.employee = employee;
    }

    editEmployee(employee: Employee) {
        console.log('------> employee' + employee.empNo);
        const modalRef = this.modalService.open(EmployeeDetailsComponent, { size: 'lg' });
        modalRef.componentInstance.employee = employee;
        modalRef.componentInstance.passEntry.subscribe((receivedEntry) => {
           console.log(receivedEntry);
        })
    }

    removeEmployee(employee: Employee) {
        if (confirm(`Are you sure you want to delete ${employee.firstName} ${employee.lastName}?`)) {
           console.log('Remove employee: ' + employee.empNo);
        }
    }
}
