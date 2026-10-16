import { Departments } from './departments';

describe('Departments', () => {
  it('should create an instance', () => {
    expect(new Departments('D001', 'Human Resources')).toBeTruthy();
  });
});
