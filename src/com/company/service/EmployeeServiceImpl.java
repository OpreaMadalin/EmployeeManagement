package com.company.service;

import com.company.exception.EmployeeDoesNotExistException;
import com.company.mapper.EmployeeMapper;
import com.company.model.Department;
import com.company.model.Employee;
import com.company.repository.EmployeeRepository;
import com.company.repository.EmployeeRepositoryImpl;

import java.util.ArrayList;
import java.util.List;


public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    private final EmployeeMapper employeeMapper = new EmployeeMapper();


    @Override
    public void listEmployees() {
        List<String> csvLines = employeeRepository.readCsv();
        for (String csvLine : csvLines) {
            Employee employee = employeeMapper.getEmployeeFromCsvLine(csvLine);
            if (employee != null) {
                System.out.println(employee.toString());
            }
        }
    }

    @Override
    public void addEmployees(Employee employee) {
        employee.setId(generateId());
        String employeeString = employeeMapper.getCsvLineFromEmployee(employee);
        employeeRepository.insertLine(employeeString);
    }

    @Override
    public void deleteEmployee(Integer id) throws EmployeeDoesNotExistException {
        List<Employee> employees = employeeMapper.getEmployeeList(employeeRepository.readCsv());
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i <= employees.size() - 1; i++) {
            values.add(Integer.parseInt(String.valueOf(employees.get(i).getId())));
        }
        if (values.contains(id)) {
            employeeRepository.deleteLine(id);
            System.out.println("Employee " + employees.get(id - 1).getName() + " was deleted!");
        } else {
            throw new EmployeeDoesNotExistException();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        String employeeString = employeeMapper.getCsvLineFromEmployee(employee);
        employeeRepository.updateLine(employee.getId(), employeeString);
    }


    @Override
    public Double calculateAverageSalary() {
        List<Employee> employees = employeeMapper.getEmployeeList(employeeRepository.readCsv());
        return calculateAverageSalary(employees);
    }

    @Override
    public Integer generateId() {
        List<Employee> employees = employeeMapper.getEmployeeList(employeeRepository.readCsv());
        Integer id = 0;
        for (Employee employee : employees) {
            id = employee.getId();
        }
        return id + 1;
    }

    @Override
    public Double calculateAverageSalaryByDepartment(Department department) {
        List<Employee> employees = employeeMapper.getEmployeeList(employeeRepository.readCsv());
        List<Employee> filterEmployees = new ArrayList<>();
        Double salarySum = 0.0;
        for (Employee employee : employees) {
            if (employee != null) {
                if (employee.getDepartment().equals(department)) {
                    filterEmployees.add(employee);
                }
            }
        }
        for (Employee employee : filterEmployees) {
            salarySum += employee.getSalary();
        }
        if (filterEmployees.size() < 1) {
            return 0.0;
        }
        return salarySum / filterEmployees.size();
    }

    private Double calculateAverageSalary(List<Employee> employees) {
        Double salarySum = 0.0;
        Integer employeesSize = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                salarySum += employee.getSalary();
                employeesSize = employee.getId();
            }
        }
        if (employees.size() < 1) {
            return 0.0;
        }
        return salarySum / employeesSize;
    }

    @Override
    public void showMostExpensiveEmployees() {
        List<Employee> employees = employeeMapper.getEmployeeList(employeeRepository.readCsv());
        List<Employee> sortedEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee != null) {
                sortedEmployees.add(employee);
            }
        }
        sortedEmployees.sort(new EmployeeSalaryComparator());
        int i = 0;
        try {
            for (i = 0; i < 10; i++) {
                System.out.println(i + 1 + ": " + sortedEmployees.get(i).getName() + " " + sortedEmployees.get(i).getSalary());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("!! " + i + " employees in database !!");
        }
    }

    @Override
    public void showMostLoyalEmployees() {
        List<Employee> employees = employeeMapper.getEmployeeList(employeeRepository.readCsv());
        List<Employee> sortedEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee != null) {
                sortedEmployees.add(employee);
            }
        }
        sortedEmployees.sort(new EmployeeLoyalComparator());
        int i = 0;
        try {
            for (i = 0; i < 10; i++) {
                System.out.println(i + 1 + ": " + sortedEmployees.get(i).getName() + " " + sortedEmployees.get(i).getEmploymentDate());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("!! " + i + " employees in database !!");
        }
    }
}

