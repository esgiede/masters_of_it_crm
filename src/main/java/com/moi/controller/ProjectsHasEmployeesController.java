package com.moi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.moi.entity.Client;
import com.moi.entity.Project;
import com.moi.entity.ProjectsHasEmployees;
import com.moi.service.ProjectsHasEmployeesService;

@Controller
public class ProjectsHasEmployeesController {

	@Autowired
	private ProjectsHasEmployeesService projectsHasEmployeesService;

	@GetMapping("phe/{id}")
	public ResponseEntity<ProjectsHasEmployees> getPheById(@PathVariable("id") Integer id) {
		ProjectsHasEmployees phe = projectsHasEmployeesService.getPheById(id);
		return new ResponseEntity<ProjectsHasEmployees>(phe, HttpStatus.OK);
	}

	@GetMapping("phe")
	public ResponseEntity<List<ProjectsHasEmployees>> getAllPhe() {
		List<ProjectsHasEmployees> list = projectsHasEmployeesService.getAllPhe();
		return new ResponseEntity<List<ProjectsHasEmployees>>(list, HttpStatus.OK);

	}

	@PostMapping("phe")
	public ResponseEntity<Void> addProjectsHasEmployees(@RequestBody ProjectsHasEmployees phe,
			UriComponentsBuilder builder) {
		boolean flag = projectsHasEmployeesService.addProjectsHasEmployees(phe);
        if (flag == false) {
	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/phe/{id}").buildAndExpand(phe.getPheId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("phe")
	public ResponseEntity<ProjectsHasEmployees> updateProjectsHasEmployees(@RequestBody ProjectsHasEmployees phe) {
		projectsHasEmployeesService.updateProjectsHasEmployees(phe);
		return new ResponseEntity<ProjectsHasEmployees>(phe, HttpStatus.OK);
	}
	@DeleteMapping("phe/{id}")
	public ResponseEntity<Void> deleteProjectsHasEmployees(@PathVariable("id") Integer id) {
		projectsHasEmployeesService.deleteProjectsHasEmployees(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
