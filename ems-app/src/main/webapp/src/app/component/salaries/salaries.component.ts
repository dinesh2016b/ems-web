import { Component, OnInit } from '@angular/core';
import { Salaries } from '../../model/salaries';
import { SalariesService } from '../../service/salaries.service';

@Component({
  selector: 'app-salaries',
  templateUrl: './salaries.component.html',
  styleUrls: ['./salaries.component.css']
})

export class SalariesComponent implements OnInit {
  title = "Salaries List";
  salaries: Salaries[];

  constructor(private salariesService: SalariesService) { 
  }

  ngOnInit() {
    this.salariesService.findAll().subscribe(data => {
      this.salaries = data;
    });
  }

  onAddSalary() {
    console.log('Add new salary record');
  }

  editSalary(salary: Salaries) {
    console.log('Edit salary: ' + salary.emp_no);
  }

  removeSalary(salary: Salaries) {
    if (confirm(`Are you sure you want to delete this salary record?`)) {
      console.log('Delete salary: ' + salary.emp_no);
    }
  }
}