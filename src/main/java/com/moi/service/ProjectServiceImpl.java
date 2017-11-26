package com.moi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moi.dao.ProjectDAO;
import com.moi.entity.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDAO projectDAO;

	public List<Project> getAllProjects() {
		return projectDAO.getAllProjects();
	}
	
	public synchronized boolean addProject(Project project) {
		projectDAO.addProject(project);
		return true;
	}
}
