package com.moi.service;

import java.util.List;

import com.moi.entity.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	Employee getEmployeeById(Long employeeId);
	boolean addEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployee(Long employeeId);
}
