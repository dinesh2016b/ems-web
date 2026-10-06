import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Departments } from '../../../model/departments';
import { DepartmentService } from '../../../service/department.service';

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.css']
})

export class DepartmentListComponent implements OnInit {
  TOKEN_KEY = 'auth-token';
  departments: Departments[];

  constructor(private departmentService: DepartmentService, private router: Router) { }

  ngOnInit() {
    let jwtToken = localStorage.getItem(this.TOKEN_KEY);
    if (jwtToken == null || jwtToken === '' || jwtToken != undefined) {
      this.departmentService.findAll(jwtToken).subscribe(data => {
        this.departments = data;
        sessionStorage.setItem("departments", JSON.stringify(this.departments));
      });
    }
  }

  onAddDepartment() {
    this.router.navigate(['/departments/add']);
  }

  editDepartment(department: Departments) {
    console.log('Edit department: ' + department.deptNo);
  }

  removeDepartment(department: Departments) {
    if (confirm(`Are you sure you want to delete ${department.deptName}?`)) {
      console.log('Delete department: ' + department.deptNo);
    }
  }

  viewDepartmentDetails(department: Departments) {
    console.log('View department details: ' + department.deptNo);
  }
}
