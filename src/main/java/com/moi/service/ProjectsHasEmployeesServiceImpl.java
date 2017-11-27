package com.moi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moi.dao.ProjectsHasEmployeesDAO;
import com.moi.entity.ProjectsHasEmployees;

@Service
public class ProjectsHasEmployeesServiceImpl implements ProjectsHasEmployeesService {

	@Autowired
	private ProjectsHasEmployeesDAO projectsHasEmployeesDAO;
	
	public List<ProjectsHasEmployees> getAllPhe() {
		return projectsHasEmployeesDAO.getAllPhe();
	}

	public synchronized boolean addProjectsHasEmployees(ProjectsHasEmployees phe) {
		 if (projectsHasEmployeesDAO.projectExist(phe.getEmployeeId(), phe.getProjectId())) {
	            return false;
         } else {
	            projectsHasEmployeesDAO.addProjectsHasEmployees(phe);
	            return true;
         }
	}

	public ProjectsHasEmployees getPheById(int pheId) {
		ProjectsHasEmployees obj = projectsHasEmployeesDAO.getPheById(pheId);
		return obj;
	}

	public void updateProjectsHasEmployees(ProjectsHasEmployees phe) {
		projectsHasEmployeesDAO.updateProjectHasEmployees(phe);
	}

	public void deleteProjectsHasEmployees(int pheId) {
		projectsHasEmployeesDAO.deleteProjectsHasEmployees(pheId);
	}
}
