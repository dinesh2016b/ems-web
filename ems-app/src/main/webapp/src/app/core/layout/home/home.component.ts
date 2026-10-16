import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  recentActivities = [
    {
      title: 'New employee added',
      type: 'success',
      icon: 'fas fa-user-plus',
      time: '2 hours ago',
      user: 'Admin'
    },
    {
      title: 'Salary report generated',
      type: 'info',
      icon: 'fas fa-file-pdf',
      time: '5 hours ago',
      user: 'Finance Team'
    },
    {
      title: 'Department updated',
      type: 'warning',
      icon: 'fas fa-building',
      time: '1 day ago',
      user: 'Manager'
    },
    {
      title: 'Employee promoted',
      type: 'primary',
      icon: 'fas fa-arrow-up',
      time: '2 days ago',
      user: 'HR Department'
    },
    {
      title: 'Attendance report submitted',
      type: 'success',
      icon: 'fas fa-check-circle',
      time: '3 days ago',
      user: 'Attendance Team'
    }
  ];

  constructor() { }

  ngOnInit(): void {
  }

  onQuickAction(action: string): void {
    console.log(`Quick action: ${action}`);
    // Add your quick action logic here
  }

}
