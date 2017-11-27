package com.moi.dao;

import java.util.List;

import com.moi.entity.ProjectsHasEmployees;

public interface ProjectsHasEmployeesDAO {
	List<ProjectsHasEmployees> getAllPhe();
	ProjectsHasEmployees getPheById(int pheId);
	void addProjectsHasEmployees(ProjectsHasEmployees phe);
	void updateProjectHasEmployees(ProjectsHasEmployees phe);
	void deleteProjectsHasEmployees(int pheId);
	boolean projectExist(int projectId, int employeeId);
}
