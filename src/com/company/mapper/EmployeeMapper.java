package com.company.mapper;

import com.company.model.Department;
import com.company.model.Employee;

import java.time.LocalDate;


import java.util.ArrayList;

import java.util.List;

import static com.company.util.Constants.COMMA_DELIMITER;

public class EmployeeMapper {
    public Employee getEmployeeFromCsvLine(String csvLine) {
        if (csvLine != null && csvLine.length() > 0) {
            String[] values = csvLine.split(COMMA_DELIMITER);
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(values[0]));
            employee.setName(values[1]);
            employee.setGender(values[2]);
            employee.setDateOfBirth(LocalDate.parse(values[3]));
            employee.setPhoneNumber(values[4]);
            switch (values[5]) {
                case "IT":
                    employee.setDepartment(Department.IT);
                    break;
                case "HR":
                    employee.setDepartment(Department.HR);
                    break;
                case "SALES":
                    employee.setDepartment(Department.SALES);
                    break;
                default:
                    employee.setDepartment(Department.MARKETING);
                    break;
            }
            employee.setEmploymentDate(LocalDate.parse(values[6]));
            employee.setSalary(Double.parseDouble(values[7]));

            return employee;

        } else {
            return null;
        }

    }

    public String getCsvLineFromEmployee(Employee employee) {
        StringBuilder sb = new StringBuilder();
        sb.append(employee.getId());
        sb.append(COMMA_DELIMITER);
        sb.append(employee.getName());
        sb.append(COMMA_DELIMITER);
        sb.append(employee.getGender());
        sb.append(COMMA_DELIMITER);
        sb.append(employee.getDateOfBirth());
        sb.append(COMMA_DELIMITER);
        sb.append(employee.getPhoneNumber());
        sb.append(COMMA_DELIMITER);
        sb.append(employee.getDepartment());
        sb.append(COMMA_DELIMITER);
        sb.append(employee.getEmploymentDate().toString());
        sb.append(COMMA_DELIMITER);
        sb.append(employee.getSalary());
        return sb.toString();
    }

    public List<Employee> getEmployeeList(List<String> stringEmployees) {
        List<Employee> employees = new ArrayList<>();
        for (String employeeString : stringEmployees) {
            employees.add(getEmployeeFromCsvLine(employeeString));
        }
        return employees;
    }
}
