package com.company.input_example.company;

import com.company.input_example.framework.InputField;

import java.util.Objects;

public class Employee {

    @InputField(inputText = "Enter your first first name:")
    private String firstName;

    @InputField(inputText = "Enter your last name:")
    private String lastName;

    @InputField(inputText = "Enter your favourite color:")
    private String color;

    @InputField(inputText = "Enter your salary:")
    private Double salary;

    //constructor with args
    //constructor without args (default)

    //getters/setters
    //toString()


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName);
    }

    public Employee(String firstName, String lastName, String color, Double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.color = color;
        this.salary = salary;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", color='" + color + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
