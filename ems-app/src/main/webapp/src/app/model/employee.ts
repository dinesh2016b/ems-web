import { Departments } from '../model/departments';
import { Salaries } from '../model/salaries';

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
