import { Departments } from './departments';
import { Salaries } from './salaries';

export class Employee {
    empNo: string;
    firstName: string;
    lastName: string;
    birthDate: string;
    hireDate: string;
    active: boolean;
    departmentsBean: Departments;
    salariesBean: Salaries;
    pageNo: string;
    size: string;
}
