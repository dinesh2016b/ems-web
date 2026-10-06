import { Component, OnInit, Input } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

import { Employee } from '../../../model/employee';
import { EmployeeService } from '../../../service/employee.service';
import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';
import { TokenStorageService } from '../../../service/token-storage.service';
import { GlobalConstants } from 'src/app/common/global-constants';

@Component({
    selector: 'app-employee-list',
    templateUrl: './employee-list.component.html',
    styleUrls: ['./employee-list.component.css']
})

export class EmployeeListComponent implements OnInit {
    TOKEN_KEY = 'auth-token';
    employees: Employee[] = [];

    constructor(private modalService: NgbModal, private employeeService: EmployeeService,
        private tokenStorageService: TokenStorageService, private router: Router) {
    }

    /*
    ngOnInit() {
       console.log('Employee -->> ngOnInit()');
       let employeeRequest: Employee ={
           "empNo": "0",
           "firstName": "",
           "lastName": "",
           "birthDate": "",
           "pageNo": "1",
           "size": "20"
       }; 
        let jwtToken = localStorage.getItem(this.TOKEN_KEY);
        console.log('JWT Token: ', jwtToken);
        if (jwtToken != null && jwtToken !== '') {
            console.log('Fetching employees from endpoint: ', GlobalConstants.ENDPOINT_EMPLOYEE_URL+'/all');
            this.employeeService.findAll(jwtToken, employeeRequest).subscribe(data => {
                console.log('Fetched employees: ', data);
                console.log('Number of employees fetched: ', data.length);
                this.employees = data ?? [];
                sessionStorage.setItem("employees", JSON.stringify(this.employees));
            });
        }
    }
*/

   ngOnInit() {
       console.log('Employee -->> ngOnInit()');
      
        let jwtToken = localStorage.getItem(this.TOKEN_KEY);
        console.log('JWT Token: ', jwtToken);
        if (jwtToken == null || jwtToken === '' || jwtToken != undefined) {
            this.employeeService.findAllEmployee(jwtToken).subscribe(data => {
                console.log('Fetched employees: ', data);
                console.log('Number of employees fetched: ', data.length);
                this.employees = data ?? [];
                sessionStorage.setItem("employees", JSON.stringify(this.employees));
            });
        }
    }
    onAddEmployee() {
        // Navigate to Add Employee page
        this.router.navigate(['/employees/add']);
    }

    redirectToEmployeeDetailsPage(employee: Employee) {
        sessionStorage.setItem("employees", JSON.stringify(this.employees));
        console.log('Employee -->> ' + employee);
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