package com.moi.dao;

import java.util.List;

import com.moi.entity.Project;

public interface ProjectDAO {
	List<Project> getAllProjects();
	Project getProjectById(int projectId);
	void addProject(Project project);
	void updateProject(Project project);
	void deleteProject(int projectId);
}
