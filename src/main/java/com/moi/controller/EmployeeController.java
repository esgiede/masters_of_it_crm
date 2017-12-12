package com.moi.controller;

import java.util.List;

import com.moi.entity.dto.EmployeeCreateDTO;
import com.moi.entity.dto.EmployeeUpdateDTO;
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
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	@GetMapping("employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = employeeService.getAllEmployees();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	@PostMapping("employees")
	public ResponseEntity<Void> addEmployee(@RequestBody @DTO(EmployeeCreateDTO.class) Employee employee, UriComponentsBuilder builder) {
		employeeService.addEmployee(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	@PutMapping("employees")
	public ResponseEntity<Employee> updateEmployee(@RequestBody @DTO(EmployeeUpdateDTO.class) Employee employee){
		employeeService.updateEmployee(employee);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	@DeleteMapping("employees/{id}")
	public ResponseEntity<Void> employeeClient(@PathVariable("id") Long id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
