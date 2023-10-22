import { Component, OnInit, Input } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { Employee } from '../../../model/employee';
import { EmployeeService } from '../../../service/employee.service';
import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';
import { TokenStorageService } from '../../../service/token-storage.service';

@Component({
    selector: 'app-employee-list',
    templateUrl: './employee-list.component.html',
    styleUrls: ['./employee-list.component.css']
})

export class EmployeeListComponent implements OnInit {
    TOKEN_KEY = 'auth-token';
    employees: Employee[];

    constructor(private modalService: NgbModal, private employeeService: EmployeeService,
        private tokenStorageService: TokenStorageService) {
    }

    ngOnInit() {
       //let jwtToken = window.sessionStorage.getItem(this.TOKEN_KEY);
       let employeeRequest: Employee ={
            "empNo": "0",
            "firstName": "",
            "lastName": "",
            "birthDate": "",
            "pageNo": "1",
            "size": "20"        
       }; 
        let jwtToken = this.tokenStorageService.getToken();
        if (jwtToken == null || jwtToken === '' || jwtToken != undefined) {
            this.employeeService.findAll(jwtToken, employeeRequest).subscribe(data => {
                this.employees = data;
                sessionStorage.setItem("employees", JSON.stringify(this.employees));
            });
        }
    }

    redirectToEmployeeDetailsPage(employee: Employee) {
        //Get employee details and store in sessionStorage.setItem(employee)
        sessionStorage.setItem("employees", JSON.stringify(this.employees));
        console.log('Employee -->> ' + employee);
    }

    editEmployee(employee: Employee) {
        console.log('------> employee' + employee.empNo);
        const modalRef = this.modalService.open(EmployeeDetailsComponent);
        modalRef.componentInstance.employee = employee;
        modalRef.componentInstance.passEntry.subscribe((receivedEntry) => {
            console.log(receivedEntry);
        })
    }

    removeEmployee(event) {
        console.log('------------>> removeEmployee : ' + event);
    }
}