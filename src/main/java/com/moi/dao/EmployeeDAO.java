package com.moi.dao;

import java.util.List;

import com.moi.entity.Employee;

public interface EmployeeDAO {
	List<Employee> getAllEmployees();
	Employee getEmployeeById(int employeeId);
	void addEmployee(Employee employee);
	void UpdateEmployee(Employee employee);
	void deleteEmployee(int employeeId);
	boolean employeeExist(String name, String lastName);
}
