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
	
	public synchronized boolean addEmployee(Employee employee){
		if (employeeDAO.employeeExist(employee.getName(), employee.getLastName())) {
            return false;
        } else {
            employeeDAO.addEmployee(employee);
            return true;
        }
        }

	public Employee getEmployeeById(int employeeId) {
		Employee obj = employeeDAO.getEmployeeById(employeeId);
		return obj;
	}

	public void updateEmployee(Employee employee) {
		employeeDAO.UpdateEmployee(employee);
	}

	public void deleteEmployee(int employeeId) {
		employeeDAO.deleteEmployee(employeeId);
	}
}

