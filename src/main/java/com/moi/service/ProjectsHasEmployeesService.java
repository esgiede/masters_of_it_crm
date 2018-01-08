package com.moi.service;

import com.moi.entity.ProjectsHasEmployees;
import com.moi.errors.exceptions.ObjectAlreadyExistException;

import java.util.List;

public interface ProjectsHasEmployeesService {

    List<ProjectsHasEmployees> getAllPhe();
    ProjectsHasEmployees getPheById(Long id);
    void addPhe(ProjectsHasEmployees phe) throws ObjectAlreadyExistException;
    void updatePhe(ProjectsHasEmployees phe, Long id);
    void deletePhe(Long id);
    boolean pheExist(ProjectsHasEmployees phe);

}
