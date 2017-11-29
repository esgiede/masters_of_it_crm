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
import com.moi.service.ProjectService;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("projects/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable("id") Integer id) {
		Project project = projectService.getProjectById(id);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("projects")
	public ResponseEntity<List<Project>> getAllProjects() {
		List<Project> list = projectService.getAllProjects();
		return new ResponseEntity<List<Project>>(list, HttpStatus.OK);
		
	}
	
	@PostMapping("projects")
	public ResponseEntity<Void> addProject(@RequestBody Project project, UriComponentsBuilder builder) {
                projectService.addProject(project);
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/project/{id}").buildAndExpand(project.getProjectId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("projects")
	public ResponseEntity<Project> updateProject(@RequestBody Project project) {
		projectService.updateProject(project);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	@DeleteMapping("projects/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable("id") Integer id) {
		projectService.deleteProject(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
