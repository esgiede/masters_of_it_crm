package com.moi.dao;

import java.util.List;

import com.moi.entity.ProjectsHasEmployees;

public interface ProjectsHasEmployeesDAO {
	List<ProjectsHasEmployees> getAllPhe();
	void addProjectsHasEmployees(ProjectsHasEmployees phe);
}
