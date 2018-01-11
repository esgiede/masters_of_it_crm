package com.moi.controller;

import com.moi.entity.Project;
import com.moi.entity.dto.ProjectDTO;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;
import com.moi.service.ProjectService;
import com.moi.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        Project project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Project>> getAllProjectsByPage(Pageable pageable) {
        Page<Project> list = projectService.getAllProjectsByPage(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addProject(@RequestBody @DTO(ProjectDTO.class) Project project, UriComponentsBuilder builder) throws ObjectAlreadyExistException {
        projectService.addProject(project);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/project/{id}").buildAndExpand(project.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@RequestBody @DTO(ProjectDTO.class) Project project, @PathVariable("id") Long id) throws ObjectNotFoundException, ObjectAlreadyExistException {
        projectService.updateProject(project, id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") Long id) throws ObjectNotFoundException, ObjectDeletingException {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
