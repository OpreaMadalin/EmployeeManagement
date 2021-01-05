package com.company.service;

import com.company.exception.EmployeeDoesNotExistException;
import com.company.model.Department;
import com.company.model.Employee;


public interface EmployeeService {
    void listEmployees();

    void addEmployees(Employee employee);

    void deleteEmployee(Integer id) throws EmployeeDoesNotExistException;

    void updateEmployee(Employee employee);

    Double calculateAverageSalary();

    Integer generateId();

    Double calculateAverageSalaryByDepartment(Department department);

    void showMostExpensiveEmployees();

    void showMostLoyalEmployees();
}
