package com.company.service;

import com.company.model.Employee;

import java.util.Comparator;

public class EmployeeSalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        if (o1 != null && o2 != null) {
            return (int) (o2.getSalary() - o1.getSalary());
        }
        return 0;
    }
}
