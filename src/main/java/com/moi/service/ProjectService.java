package com.moi.service;

import java.util.List;

import com.moi.entity.Project;

public interface ProjectService {
	List<Project> getAllProjects();
	Project getProjectById(int projectId);
	boolean addProject(Project project);
	void updateProject(Project project);
	void deleteProject(int projectId);
}
