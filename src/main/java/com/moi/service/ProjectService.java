package com.moi.service;

import java.util.List;

import com.moi.entity.Project;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;

public interface ProjectService {
	List<Project> getAllProjects();
	Project getProjectById(Long id) throws ObjectNotFoundException;
	void addProject(Project project) throws ObjectAlreadyExistException;
	void updateProject(Project project, Long id) throws ObjectNotFoundException, ObjectAlreadyExistException;
	void deleteProject(Long id) throws ObjectNotFoundException, ObjectDeletingException;
	boolean projectExist(Project project);
}
