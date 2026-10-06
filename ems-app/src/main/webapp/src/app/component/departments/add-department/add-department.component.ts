import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Departments } from '../../../model/departments';
import { DepartmentService } from '../../../service/department.service';

@Component({
  selector: 'app-add-department',
  templateUrl: './add-department.component.html',
  styleUrls: ['./add-department.component.css']
})
export class AddDepartmentComponent implements OnInit {
  department: Departments = new Departments('', '');

  constructor(private departmentService: DepartmentService, private router: Router) { }

  ngOnInit(): void {
  }

  save() {
    this.departmentService.save(this.department).subscribe(res => {
      this.router.navigate(['/departments']);
    }, err => {
      console.error('Failed to save department', err);
    });
  }

  cancel() {
    this.router.navigate(['/departments']);
  }
}
