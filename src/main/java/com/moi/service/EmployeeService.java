package com.moi.service;

import com.moi.entity.Employee;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
	Page<Employee> getAllEmployeesByPage(Pageable pageable);
	Employee getEmployeeById(Long id) throws ObjectNotFoundException;
	void addEmployee(Employee employee) throws ObjectAlreadyExistException;
	void updateEmployee(Employee employee, Long id) throws ObjectNotFoundException;
	void deleteEmployee(Long id) throws ObjectNotFoundException, ObjectDeletingException;
	boolean employeeExist(Employee employee);
}
