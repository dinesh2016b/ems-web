import { Component, OnInit, Input, Output, EventEmitter, Optional } from '@angular/core';
import { Employee } from './../../../model/employee';
import { EmployeeService } from './../../../service/employee.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { GlobalConstants } from 'src/app/common/global-constants';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})

export class EmployeeDetailsComponent implements OnInit {
  TOKEN_KEY = 'auth-token';
  @Input() public employee: Employee;
  @Output() passEntry: EventEmitter<any> = new EventEmitter();

  constructor(
    @Optional() public activeModal: NgbActiveModal | null,
    private employeeService: EmployeeService,
    private route: ActivatedRoute
  ) { }

  public closeMe() {
    if (this.activeModal) {
      this.activeModal.close(this.employee);
    }
  }

  public updateEmployee() {
     console.log('EmployeeDetailsComponent -->> updateEmployee()');
    this.passEntry.emit(this.employee);
    let jwtToken = localStorage.getItem(this.TOKEN_KEY);
    if (jwtToken != null && jwtToken !== '') {
      this.employeeService.save(jwtToken, this.employee).subscribe(res => {
        console.log('-------> employee.empNo      ----> ' + this.employee.empNo);
        console.log('-------> employee.firstName  ----> ' + this.employee.firstName);
        console.log('-------> employee.lastName   ----> ' + this.employee.lastName);
        console.log('-------> employee.bithdate   ----> ' + this.employee.birthDate);
      });
    }
    if (this.activeModal) {
      this.activeModal.close(this.employee);
    }
  }

  ngOnInit() {
    console.log('EmployeeDetailsComponent.Employee -->> ngOnInit()');
    /*console.log('Employee -->> ', this.employee);
    // If employee was passed in (modal usage), keep it
    if (this.employee && this.employee.empNo) {
      return;
    }*/

    // Try to read id from route params (when opened via navigation)
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      console.log('Employee id -->> '+ id);
      /*const employeesJson = sessionStorage.getItem('employees');
      if (employeesJson) {
        try {
          const employees = JSON.parse(employeesJson) as Employee[];
          console.log('Employee -->> ', this.employees);
          const found = employees.find(e => e && e.empNo === id);
          if (found) {
            this.employee = found;
            return;
          }
        } catch (err) {
          console.error('Error parsing employees from sessionStorage', err);
        }
      }*/
    
      // Fallback: attempt backend fetch if session storage not available
      const jwtToken = localStorage.getItem(this.TOKEN_KEY);
      if (jwtToken != null && jwtToken !== '') {
        this.employeeService.findById(jwtToken, id).subscribe(data => {
          if (Array.isArray(data)) {
            this.employee = data[0] as Employee;
          } else {
            this.employee = data as Employee;
          }
          // store loaded employee for later navigation
          sessionStorage.setItem('employees', JSON.stringify([this.employee]));
        }, err => {
          console.error('Failed to load employee by id', err);
        });
      }
    } else {
      // Ensure template bindings don't break
     // this.employee = this.employee || { empNo: '', firstName: '', lastName: '', birthDate: '', pageNo: '', size: '' } as Employee;
     console.log('Employee -->> no id provided, using default employee', this.employee);
    }
  }
}
