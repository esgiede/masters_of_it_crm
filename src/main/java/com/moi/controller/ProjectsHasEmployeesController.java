package com.moi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.moi.entity.ProjectsHasEmployees;
import com.moi.service.ProjectsHasEmployeesService;

@Controller
public class ProjectsHasEmployeesController {

	@Autowired
	private ProjectsHasEmployeesService projectsHasEmployeesService;
	
	@GetMapping("phe")
	public ResponseEntity<List<ProjectsHasEmployees>> getAllPhe() {
		List<ProjectsHasEmployees> list = projectsHasEmployeesService.getAllPhe();
		return new ResponseEntity<List<ProjectsHasEmployees>>(list, HttpStatus.OK);
		
	}
}
