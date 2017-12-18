package com.moi.service;

import java.util.List;

import com.moi.entity.Project;

public interface ProjectService {
	List<Project> getAllProjects();
	Project getProjectById(Long id);
	void addProject(Project project);
	void updateProject(Project project, Long id);
	void deleteProject(Long id);
}
