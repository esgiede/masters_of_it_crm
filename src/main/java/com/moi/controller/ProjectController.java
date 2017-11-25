package com.moi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	
}
