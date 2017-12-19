package com.moi.controller;

import java.util.List;

import com.moi.entity.dto.EmployeeDTO;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectNotFoundException;
import com.moi.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.moi.entity.Employee;
import com.moi.service.EmployeeService;

@Controller
@RequestMapping("employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) throws ObjectNotFoundException {
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = employeeService.getAllEmployees();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	@PostMapping
	public ResponseEntity<Void> addEmployee(@RequestBody @DTO(EmployeeDTO.class) Employee employee, UriComponentsBuilder builder) throws ObjectAlreadyExistException {
		employeeService.addEmployee(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody @DTO(EmployeeDTO.class) Employee employee, @PathVariable("id") Long id) throws ObjectNotFoundException, ObjectAlreadyExistException {
		employeeService.updateEmployee(employee, id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> employeeClient(@PathVariable("id") Long id) throws ObjectNotFoundException {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
