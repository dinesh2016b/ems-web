import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../../../model/employee';
import { EmployeeService } from '../../../service/employee.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {
  employee: Employee = { empNo: '', firstName: '', lastName: '', birthDate: '', pageNo: '1', size: '20' };
  TOKEN_KEY = 'auth-token';

  constructor(private employeeService: EmployeeService, private router: Router) { }

  ngOnInit(): void {
  }

  save() {
    const jwtToken = localStorage.getItem(this.TOKEN_KEY);
    this.employeeService.save(jwtToken, this.employee).subscribe(res => {
      // on success navigate back to list
      this.router.navigate(['/employees']);
    }, err => {
      console.error('Failed to save employee', err);
    });
  }

  cancel() {
    this.router.navigate(['/employees']);
  }
}
