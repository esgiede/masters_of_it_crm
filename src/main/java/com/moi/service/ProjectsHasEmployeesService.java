package com.moi.service;

import java.util.List;

import com.moi.entity.ProjectsHasEmployees;

public interface ProjectsHasEmployeesService {
	List<ProjectsHasEmployees> getAllPhe();
	boolean addProjectsHasEmployees(ProjectsHasEmployees phe);
}
