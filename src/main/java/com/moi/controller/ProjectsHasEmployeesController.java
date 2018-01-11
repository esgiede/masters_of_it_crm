package com.moi.controller;

import com.moi.entity.ProjectsHasEmployees;
import com.moi.entity.dto.ProjectsHasEmployeesDTO;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;
import com.moi.service.ProjectsHasEmployeesService;
import com.moi.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("phe")
public class ProjectsHasEmployeesController {

    @Autowired
    ProjectsHasEmployeesService projectsHasEmployeesService;

    @GetMapping
    public ResponseEntity<List<ProjectsHasEmployees>> getAllPhe() {
        List<ProjectsHasEmployees> list = projectsHasEmployeesService.getAllPhe();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectsHasEmployees> getPheById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        ProjectsHasEmployees projectsHasEmployees = projectsHasEmployeesService.getPheById(id);
        return new ResponseEntity<>(projectsHasEmployees, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addPhe(@RequestBody @DTO(ProjectsHasEmployeesDTO.class) ProjectsHasEmployees projectsHasEmployees,
                                       UriComponentsBuilder builder) throws ObjectAlreadyExistException {
        projectsHasEmployeesService.addPhe(projectsHasEmployees);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/phe/{id}").buildAndExpand(projectsHasEmployees.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectsHasEmployees> updatePhe(@RequestBody @DTO(ProjectsHasEmployeesDTO.class)
                                                                  ProjectsHasEmployees projectsHasEmployees,
                                                          @PathVariable("id") Long id) throws ObjectNotFoundException,
            ObjectAlreadyExistException {
        projectsHasEmployeesService.updatePhe(projectsHasEmployees, id);
        return new ResponseEntity<>(projectsHasEmployees, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhe(@PathVariable("id") Long id) throws ObjectNotFoundException, ObjectDeletingException {
        projectsHasEmployeesService.deletePhe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
