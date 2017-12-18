package com.moi.service;

import java.util.List;

import com.moi.entity.Project;
import com.moi.errors.exceptions.ObjectNotFoundException;

public interface ProjectService {
	List<Project> getAllProjects();
	Project getProjectById(Long id) throws ObjectNotFoundException;
	void addProject(Project project);
	void updateProject(Project project, Long id) throws ObjectNotFoundException;
	void deleteProject(Long id) throws ObjectNotFoundException;
}
