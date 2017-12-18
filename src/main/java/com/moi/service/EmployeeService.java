package com.moi.service;

import java.util.List;

import com.moi.entity.Employee;
import com.moi.errors.exceptions.ObjectNotFoundException;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	Employee getEmployeeById(Long id) throws ObjectNotFoundException;
	void addEmployee(Employee employee);
	void updateEmployee(Employee employee, Long id) throws ObjectNotFoundException;
	void deleteEmployee(Long id) throws ObjectNotFoundException;
}
