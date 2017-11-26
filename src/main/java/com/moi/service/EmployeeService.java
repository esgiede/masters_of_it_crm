package com.moi.service;

import java.util.List;

import com.moi.entity.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	boolean addEmployee(Employee employee);
}
