import { Component, OnInit, Input, Output, EventEmitter, Optional } from '@angular/core';
import { Employee } from '../../../../core/model/employee';
import { EmployeeService } from '../../service/employee.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { GlobalConstants } from 'src/app/core/common/global-constants';
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
    // Try to read id from route params (when opened via navigation)
    //const id = this.route.snapshot.paramMap.get('id');

    const employeesJson = localStorage.getItem('currentEmployee');
    console.log('currentEmployee -->> '+ employeesJson);

    if (employeesJson == null) {
    /*
      if (employeesJson != null) {
        try {
          const employee = JSON.parse(employeesJson) as Employee[];
          console.log('Employee -->> ', this.employee);
          const found = employee.find(e => e && e.empNo === id);
          if (found) {
            this.employee = found;
            return;
          }
        } catch (err) {
          console.error('Error parsing employees from sessionStorage', err);
        }
      }
    */
      // Fallback: attempt backend fetch if session storage not available
      const jwtToken = localStorage.getItem(this.TOKEN_KEY);
      if (jwtToken != null && jwtToken !== '') {
        this.employeeService.findById(jwtToken, this.employee.empNo).subscribe(data => {
          if (Array.isArray(data)) {
            this.employee = data[0] as Employee;
          } else {
            this.employee = data as Employee;
          }
          // store loaded employee for later navigation
          localStorage.setItem('currentEmployee', JSON.stringify([this.employee]));
          console.log('currentEmployee -->> '+ JSON.stringify([this.employee]));
        }, err => {
          console.error('Failed to load employee by number', err);
        });
      }
    } else {
      // Ensure template bindings don't break
     // this.employee = this.employee || { empNo: '', firstName: '', lastName: '', birthDate: '', pageNo: '', size: '' } as Employee;
     localStorage.setItem('currentEmployee', JSON.stringify([this.employee]));
     console.log('Employee -->> no id provided, using default employee', this.employee);
    }
  }
}
