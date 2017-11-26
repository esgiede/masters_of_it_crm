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

}
