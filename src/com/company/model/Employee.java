package com.company.model;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private Integer id;
    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private Department department;
    private LocalDate employmentDate;
    private Double salary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee1 = (Employee) o;
        return Objects.equals(id, employee1.id) && Objects.equals(name, employee1.name) && Objects.equals(gender, employee1.gender) && Objects.equals(dateOfBirth, employee1.dateOfBirth) && Objects.equals(phoneNumber, employee1.phoneNumber) && department == employee1.department && Objects.equals(employmentDate, employee1.employmentDate) && Objects.equals(salary, employee1.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, dateOfBirth, phoneNumber, department, employmentDate, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber=" + phoneNumber +
                ", department=" + department +
                ", employmentDate=" + employmentDate +
                ", salary=" + salary +
                '}';
    }
}