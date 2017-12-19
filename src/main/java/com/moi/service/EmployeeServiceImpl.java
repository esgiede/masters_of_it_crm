package com.moi.service;

import java.util.List;

import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectNotFoundException;
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

	public List<Employee> getAllEmployees() { return employeeRepository.findAll(); }
	public synchronized void addEmployee(Employee employee) throws ObjectAlreadyExistException {
        if(employeeExist(employee) == false){
			employeeRepository.save(employee);
		}else{
        	throw new ObjectAlreadyExistException("Pracownik o podanych danych już istnieje");
		}

	}
	public Employee getEmployeeById(Long id) throws ObjectNotFoundException {
		if (employeeRepository.exists(id) == true){
			return employeeRepository.findOne(id);
		}else{
			throw new ObjectNotFoundException("Nie znaleziono pracownika o podanym Id");
		}
	}
	public void updateEmployee(Employee employee, Long id) throws ObjectNotFoundException, ObjectAlreadyExistException {
		if(employeeRepository.exists(id) == true){
			if(employeeExist(employee) == false){
				employee.setId(id);
				employeeRepository.save(employee);
			}else{
				throw new ObjectAlreadyExistException("Pracownik o podanych danych już istnieje");
			}

		}else{
			throw new ObjectNotFoundException("Nie znaleziono pracownika o podanym Id");
		}
	}
	public void deleteEmployee(Long id) throws ObjectNotFoundException {
		if(employeeRepository.exists(id) == true){
			employeeRepository.delete(id);
		}else{
			throw new ObjectNotFoundException("Nie znaleziono pracownika o podanym Id");
		}
	}
	public boolean employeeExist(Employee employee){
		boolean isExist = false;

		for(Employee temp : employeeRepository.findAll()){
			if(temp.getName().equalsIgnoreCase(employee.getName()) && temp.getLastName().equalsIgnoreCase(employee.getLastName())){
				isExist = true;
			}
		}
		return isExist;
	}
}

