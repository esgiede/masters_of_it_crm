package com.moi.service;

import java.util.List;

import com.moi.entity.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	Employee getEmployeeById(Long id);
	void addEmployee(Employee employee);
	void updateEmployee(Employee employee, Long id);
	void deleteEmployee(Long id);
}
