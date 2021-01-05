package com.company.service;

import com.company.model.Employee;

import java.util.Comparator;

public class EmployeeLoyalComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        if (o1 != null && o2 != null) {
            return o1.getEmploymentDate().compareTo(o2.getEmploymentDate());
        }
        return 0;
    }
}
