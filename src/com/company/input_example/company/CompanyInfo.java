package com.company.input_example.company;

import com.company.input_example.framework.GetAll;
import com.company.input_example.framework.Input;
import com.company.input_example.framework.Save;

import java.util.HashSet;
import java.util.Set;

public class CompanyInfo {

    private static Set<Employee> employees = new HashSet<>();

    @Save
    public void save(@Input Employee employee) {
        employees.add(employee);
    }

    @GetAll
    public Set<Employee> getAll() {
        return employees;
    }

    public Employee findByName(String name) {
        return employees.stream()
                .filter(x -> x.getFirstName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
