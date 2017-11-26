package com.moi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.moi.entity.Project;
import com.moi.service.ProjectService;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("projects")
	public ResponseEntity<List<Project>> getAllProjects() {
		List<Project> list = projectService.getAllProjects();
		return new ResponseEntity<List<Project>>(list, HttpStatus.OK);
		
	}
	
	@PostMapping("project")
	public ResponseEntity<Void> addProject(@RequestBody Project project, UriComponentsBuilder builder) {
                boolean flag = projectService.addProject(project);
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/project/{id}").buildAndExpand(project.getProjectId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
}
