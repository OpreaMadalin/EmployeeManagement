package com.company.service;

import com.company.exception.EmployeeDoesNotExistException;
import com.company.exception.InvalidEmployeeGenderException;
import com.company.exception.InvalidPhoneNumberFormatException;
import com.company.exception.InvalidUserInteractionException;
import com.company.mapper.EmployeeMapper;
import com.company.model.Department;
import com.company.model.Employee;
import com.company.repository.EmployeeRepository;
import com.company.repository.EmployeeRepositoryImpl;
import com.company.util.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class EmployeeInteractionServiceImpl implements EmployeeInteractionService {
    EmployeeMapper employeeMapper = new EmployeeMapper();
    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Employee addEmployeeAction() {
        Employee employee = new Employee();
        employee.setName(getName());
        employee.setGender(getGender());
        employee.setDateOfBirth(getDateOfBirth());
        employee.setPhoneNumber(getPhoneNumber());
        employee.setDepartment(getDepartment());
        employee.setEmploymentDate(getEmploymentDate());
        employee.setSalary(getSalary());
        return employee;
    }

    @Override
    public Integer deleteEmployeeAction() {
        return getIdFromScanner();
    }

    @Override
    public Employee updateEmployeeAction() throws EmployeeDoesNotExistException {
        Employee employee = new Employee();
        List<Employee> employees = employeeMapper.getEmployeeList(employeeRepository.readCsv());
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i <= employees.size() - 1; i++) {
            values.add(Integer.parseInt(String.valueOf(employees.get(i).getId())));
        }
        Integer num = getIdFromScanner();
        if (values.contains(num)) {
            employee.setId(num);
            System.out.println("Employee " + employees.get(num - 1).getName() + " will be updated!");
        } else {
            throw new EmployeeDoesNotExistException();
        }
        employee.setName(getName());
        employee.setGender(getGender());
        employee.setDateOfBirth(getDateOfBirth());
        employee.setPhoneNumber(getPhoneNumber());
        employee.setDepartment(getDepartment());
        employee.setEmploymentDate(getEmploymentDate());
        employee.setSalary(getSalary());
        return employee;
    }


    @Override
    public Department chooseDepartmentAction() {
        return getDepartment();
    }

    private Integer getIdFromScanner() {
        System.out.println("Id: ");
        try {
            return Integer.parseInt(String.valueOf(scanner.nextLine()));
        } catch (Exception ex) {
            System.out.println("Wrong id. Try again!");
        }
        return getIdFromScanner();
    }


    private String getName() {
        System.out.println("Name:");
        try {
            return scanner.nextLine();
        } catch (Exception ex) {
            System.out.println("Wrong name! Try again!");
        }
        return getName();
    }

    private String getGender() {
        System.out.println("Gender:");
        String gender = scanner.nextLine().toUpperCase(Locale.ROOT);
        try {
            if (!gender.equals("M") && !gender.equals("F")) {
                throw new InvalidEmployeeGenderException();
            } else {
                return gender;
            }
        } catch (InvalidEmployeeGenderException e) {
            System.out.println("Wrong gender! Try again M - F!");
        }
        return getGender();
    }

    private LocalDate getDateOfBirth() {
        System.out.println("Birthday " + "(" + Constants.DATE_FORMAT + "):");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(scanner.nextLine(), format);
        } catch (Exception ex) {
            System.out.println("Wrong Birthday format! Try again!");
        }
        return getDateOfBirth();
    }

    private String getPhoneNumber() {
        System.out.println("Phone number " + "(" + Constants.PHONE_NUMBER_FORMAT + "):");
        String phoneNumber = scanner.nextLine();
        boolean match = false;
        if (phoneNumber.matches("\\d{4}[.\\s]\\d{3}[.\\s]\\d{3}")) {
            match = true;
        }
        try {
            if (match) {
                return phoneNumber;
            } else {
                throw new InvalidPhoneNumberFormatException();
            }
        } catch (InvalidPhoneNumberFormatException e) {
            System.out.println("Wrong phone Number! Try again!");
        }
        return getPhoneNumber();
    }

    private Department getDepartment() {
        System.out.println("Department: " +
                Department.IT.getValue() + " - IT, " +
                Department.HR.getValue() + " - HR, " +
                Department.SALES.getValue() + " - Sales, " +
                Department.MARKETING.getValue() + " - Marketing ");
        try {
            int depId = Integer.parseInt(scanner.nextLine());
            if (depId != 1 && depId != 2 && depId != 3 && depId != 4) {
                throw new InvalidUserInteractionException();
            }
            return depId == Department.IT.getValue() ? Department.IT :
                    depId == Department.HR.getValue() ? Department.HR :
                            depId == Department.SALES.getValue() ? Department.SALES :
                                    Department.MARKETING;
        } catch (Exception ex) {
            System.out.println("Wrong department. Try again!");
        }
        return getDepartment();
    }

    private LocalDate getEmploymentDate() {
        System.out.println("Employment date " + "(" + Constants.DATE_FORMAT + "):");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(scanner.nextLine(), format);
        } catch (Exception ex) {
            System.out.println("Wrong employment date format! Try again!");
        }
        return getEmploymentDate();
    }

    private Double getSalary() {
        System.out.println("Salary!");
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Wrong salary. Try again!");
        }
        return getSalary();
    }
}
