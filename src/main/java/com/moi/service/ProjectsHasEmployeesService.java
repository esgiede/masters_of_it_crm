package com.moi.service;

import java.util.List;

import com.moi.entity.ProjectsHasEmployees;

public interface ProjectsHasEmployeesService {
	List<ProjectsHasEmployees> getAllPhe();
	ProjectsHasEmployees getPheById(int pheId);
	boolean addProjectsHasEmployees(ProjectsHasEmployees phe);
	void updateProjectsHasEmployees(ProjectsHasEmployees phe);
	void deleteProjectsHasEmployees(int pheId);
}
