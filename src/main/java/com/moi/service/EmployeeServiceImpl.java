package com.moi.service;

import java.util.LinkedList;
import java.util.List;

import com.moi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moi.entity.Employee;

import javax.transaction.Transactional;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		List<Employee> list = new LinkedList<>();
		for(Employee empl : employeeRepository.findAll())
		{
			list.add(empl);
		}
		return list;
	}
	
	public synchronized void addEmployee(Employee employee){
            employeeRepository.save(employee);
	}

	public Employee getEmployeeById(Long id) {
		return employeeRepository.findOne(id);
	}

	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void deleteEmployee(Long id) {
		employeeRepository.delete(id);
	}
}

