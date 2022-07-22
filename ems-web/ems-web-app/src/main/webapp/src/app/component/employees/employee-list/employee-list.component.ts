import { Component, OnInit, Input } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { Employee } from '../../../model/employee';
import { EmployeeService } from '../../../service/employee.service';
import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';

@Component({
    selector: 'app-employee-list',
    templateUrl: './employee-list.component.html',
    styleUrls: ['./employee-list.component.css']
})

export class EmployeeListComponent implements OnInit {

    employees: Employee[];

    constructor(private modalService: NgbModal, private employeeService: EmployeeService) { }

    ngOnInit() {
        /*let jwt_token = localStorage.getItem("jwt");
        console.log('-----> jwt_token : '+jwt_token);
        localStorage.setItem("employees_jwt_token", jwt_token);
        sessionStorage.setItem("employees_jwt_token", jwt_token);
        */
        this.employeeService.findAll().subscribe(data => {
            this.employees = data;
        });
        localStorage.setItem("employees", JSON.stringify(this.employees));
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
        console.log(event);
    }
}