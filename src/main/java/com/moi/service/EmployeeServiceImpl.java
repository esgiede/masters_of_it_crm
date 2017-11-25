package com.moi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moi.dao.EmployeeDAO;
import com.moi.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDAO;

	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}
	
	
}
