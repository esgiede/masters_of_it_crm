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
		 if (projectDAO.projectExist(project.getName())) {
	            return false;
         } else {
	            projectDAO.addProject(project);
	            return true;
         }
	}

	public Project getProjectById(int projectId) {
		Project obj = projectDAO.getProjectById(projectId);
		return obj;
	}

	public void updateProject(Project project) {
		projectDAO.updateProject(project);
	}

	public void deleteProject(int projectId) {
		projectDAO.deleteProject(projectId);
	}
}
