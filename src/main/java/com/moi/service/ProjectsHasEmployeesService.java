package com.moi.service;

import com.moi.entity.ProjectsHasEmployees;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectNotFoundException;

import java.util.List;

public interface ProjectsHasEmployeesService {

    List<ProjectsHasEmployees> getAllPhe();
    ProjectsHasEmployees getPheById(Long id) throws ObjectNotFoundException;
    void addPhe(ProjectsHasEmployees phe) throws ObjectAlreadyExistException;
    void updatePhe(ProjectsHasEmployees phe, Long id) throws ObjectNotFoundException, ObjectAlreadyExistException;
    void deletePhe(Long id) throws ObjectNotFoundException;
    boolean pheExist(ProjectsHasEmployees phe);

}
