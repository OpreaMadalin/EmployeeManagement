package com.company.service;

import com.company.exception.EmployeeDoesNotExistException;
import com.company.exception.InvalidUserInteractionException;
import com.company.model.Department;
import com.company.model.Employee;

import java.util.Scanner;

import static com.company.util.Constants.*;

public class UserInteractionServiceImpl implements UserInteractionService {

    private final Scanner scanner = new Scanner(System.in);
    EmployeeInteractionService employeeInteractionService = new EmployeeInteractionServiceImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void initInteraction() {
        switch (chooseInitialAction()) {
            case ACCESS_DATABASE:
                switch (chooseAccessDatabaseAction()) {
                    case LIST_EMPLOYEES:
                        employeeService.listEmployees();
                        break;
                    case ADD_EMPLOYEE:
                        Employee employee = employeeInteractionService.addEmployeeAction();
                        employeeService.addEmployees(employee);
                        System.out.println("Employee was added!");
                        break;
                    case DELETE_EMPLOYEE:
                        int idToBeDeleted = employeeInteractionService.deleteEmployeeAction();
                        try {
                            employeeService.deleteEmployee(idToBeDeleted);
                        } catch (EmployeeDoesNotExistException e) {
                            System.out.println("Employee does not exist!");
                            break;
                        }
                        break;
                    case UPDATE_EMPLOYEE:
                        Employee employeeToBeUpdated;
                        try {
                            employeeToBeUpdated = employeeInteractionService.updateEmployeeAction();
                            employeeService.updateEmployee(employeeToBeUpdated);
                        } catch (EmployeeDoesNotExistException e) {
                            System.out.println("Employee does not exist!");
                            break;
                        }
                        System.out.println("Employee was updated!");
                        break;
                    case BACK_MENU:
                        initInteraction();
                        break;
                }
                break;


            case VIEW_REPORTS:
                switch (chooseViewReportAction()) {
                    case AVERAGE_SALARY_BY_COMPANY:
                        System.out.println("Show average salary by company " + employeeService.calculateAverageSalary());
                        break;
                    case AVERAGE_SALARY_BY_DEPARTMENT:
                        Department department = employeeInteractionService.chooseDepartmentAction();
                        Double averageByDepartment = employeeService.calculateAverageSalaryByDepartment(department);
                        System.out.println("Average salary for " + department + " Is " + averageByDepartment);
                        break;
                    case MOST_EXPENSIVE_EMPLOYEES:
                        employeeService.showMostExpensiveEmployees();
                        break;
                    case MOST_LOYAL_EMPLOYEES:
                        employeeService.showMostLoyalEmployees();
                        break;
                    case BACK_MENU:
                        initInteraction();
                        break;
                }
                break;
        }
        initInteraction();
    }

    private Integer chooseViewReportAction() {
        System.out.println("Average salary by company - press " + AVERAGE_SALARY_BY_COMPANY);
        System.out.println("Average salary by department - press " + AVERAGE_SALARY_BY_DEPARTMENT);
        System.out.println("Most 10 expensive employees - press " + MOST_EXPENSIVE_EMPLOYEES);
        System.out.println("Most 10 loyal employees - press " + MOST_LOYAL_EMPLOYEES);
        System.out.println("For back - press 0 !");
        try {
            int action = Integer.parseInt(scanner.nextLine());
            if (action != AVERAGE_SALARY_BY_COMPANY && action != AVERAGE_SALARY_BY_DEPARTMENT &&
                    action != MOST_EXPENSIVE_EMPLOYEES && action != MOST_LOYAL_EMPLOYEES && action != BACK_MENU) {
                throw new InvalidUserInteractionException();
            }
            return action;
        } catch (Exception e) {
            System.out.println("Please enter a valid number for your action! ");
        }
        return chooseViewReportAction();
    }

    private Integer chooseInitialAction() {
        System.out.println("Choose action: ");
        System.out.println("Access database press - press " + ACCESS_DATABASE);
        System.out.println("View Reports press - press " + VIEW_REPORTS);

        try {
            int action = Integer.parseInt(scanner.nextLine());
            if (action != ACCESS_DATABASE && action != VIEW_REPORTS) {
                throw new InvalidUserInteractionException();
            }
            return action;
        } catch (Exception ex) {
            System.out.println("Please enter a valid number: " + ACCESS_DATABASE + " (access database) " +
                    " or " + VIEW_REPORTS + " (view reports)!");
        }
        return chooseInitialAction();
    }

    private Integer chooseAccessDatabaseAction() {
        System.out.println("Add employee - press " + ADD_EMPLOYEE);
        System.out.println("List employees - press " + LIST_EMPLOYEES);
        System.out.println("Update employee - press " + UPDATE_EMPLOYEE);
        System.out.println("Delete employee - press " + DELETE_EMPLOYEE);
        System.out.println("For back - press 0 !");

        try {
            int action = Integer.parseInt(scanner.nextLine());
            if (action != ADD_EMPLOYEE && action != LIST_EMPLOYEES && action != UPDATE_EMPLOYEE &&
                    action != DELETE_EMPLOYEE && action != BACK_MENU) {
                throw new InvalidUserInteractionException();
            }
            return action;
        } catch (Exception e) {
            System.out.println("Please enter a valid number for your action!");
        }
        return chooseAccessDatabaseAction();
    }
}
