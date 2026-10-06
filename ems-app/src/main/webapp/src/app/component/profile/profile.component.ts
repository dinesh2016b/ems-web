import { Component, OnInit } from '@angular/core';
import { BackendApiService } from '../../service/backend-api.service';
import { GlobalConstants } from '../../common/global-constants';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  isEdit = false;
  user: any;
  private originalUser: any;

  constructor(private backendApiService: BackendApiService) { }

  ngOnInit(): void {
    const jwtToken = localStorage.getItem('auth-token');
    if (jwtToken) {
      this.backendApiService.sendGetAPIRequest(GlobalConstants.ENDPOINT_PROFILE_URL, jwtToken).subscribe(data => {
        if (data && Object.keys(data).length > 0) {
          this.user = data;
          this.originalUser = JSON.parse(JSON.stringify(this.user));
        } else {
          this.setDemoUser();
        }
      }, err => {
        console.warn('Profile fetch failed, using demo data', err);
        this.setDemoUser();
      });
    } else {
      this.setDemoUser();
    }
  }

  private setDemoUser() {
    this.user = {
      fullName: 'Dinesh B',
      email: 'dinesh.b@example.com',
      phone: '+1 (555) 123-4567',
      dob: '1985-01-15',
      address: '123 Main Street, City, State 12345',
      gender: 'Male',
      employeeId: 'EMP-001245',
      department: 'Engineering',
      position: 'Senior Software Engineer',
      manager: 'John Smith',
      joinDate: '2020-03-10',
      status: 'Active',
      preferences: {
        emailNotifications: true,
        smsNotifications: false,
        pushNotifications: true,
        newsletter: true
      }
    };
    this.originalUser = JSON.parse(JSON.stringify(this.user));
  }

  onEditProfile(): void {
    this.isEdit = true;
  }

  onChangePhoto(): void {
    console.log('Change photo clicked');
  }

  onSaveProfile(): void {
    const jwtToken = localStorage.getItem('auth-token');
    this.backendApiService.sendPutRequest(GlobalConstants.ENDPOINT_PROFILE_URL, { jwtToken, user: this.user }).subscribe(res => {
      console.log('Profile saved', res);
      this.originalUser = JSON.parse(JSON.stringify(this.user));
      this.isEdit = false;
    }, err => {
      console.error('Failed to save profile', err);
      alert('Failed to save profile. Please try again.');
    });
  }

  onCancel(): void {
    // revert changes
    this.user = JSON.parse(JSON.stringify(this.originalUser));
    this.isEdit = false;
  }

  onChangePassword(): void {
    console.log('Change password clicked');
  }

  onEnable2FA(): void {
    console.log('Enable 2FA clicked');
  }

  onViewSessions(): void {
    console.log('View sessions clicked');
  }

  onViewLoginHistory(): void {
    console.log('View login history clicked');
  }

  onDeactivateAccount(): void {
    if (confirm('Are you sure you want to deactivate your account?')) {
      console.log('Account deactivated');
    }
  }

  onDeleteAccount(): void {
    if (confirm('Are you sure you want to permanently delete your account? This action cannot be undone.')) {
      console.log('Account deleted');
    }
  }

}
