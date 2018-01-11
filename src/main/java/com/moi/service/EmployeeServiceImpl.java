package com.moi.service;

import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;
import com.moi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.moi.entity.Employee;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public Page<Employee> getAllEmployeesByPage(Pageable pageable) { return employeeRepository.findAll(pageable); }
	public synchronized void addEmployee(Employee employee) throws ObjectAlreadyExistException {
        if(!employeeExist(employee)){
			employeeRepository.save(employee);
		}else{
        	throw new ObjectAlreadyExistException("Pracownik o podanych danych już istnieje");
		}
	}
	public Employee getEmployeeById(Long id) throws ObjectNotFoundException {
		if (employeeRepository.exists(id)){
			return employeeRepository.findOne(id);
		}else{
			throw new ObjectNotFoundException("Nie znaleziono pracownika o podanym Id");
		}
	}
	public void updateEmployee(Employee employee, Long id) throws ObjectNotFoundException{
		if(employeeRepository.exists(id)){
			employee.setId(id);
			employeeRepository.save(employee);
		}else{
			throw new ObjectNotFoundException("Nie znaleziono pracownika o podanym Id");
		}
	}
	public void deleteEmployee(Long id) throws ObjectNotFoundException, ObjectDeletingException {
		if(employeeRepository.exists(id)){
			if(employeeRepository.findOne(id).getProjectsHasEmployees().isEmpty()){
				employeeRepository.delete(id);
			}else {
				throw new ObjectDeletingException("Klient posiada przypisane projekty.");
			}
		}else{
			throw new ObjectNotFoundException("Nie znaleziono klienta o podanym Id");
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

