package com.moi.service;

import java.util.List;

import com.moi.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moi.entity.Project;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public List<Project> getAllProjects() { return projectRepository.findAll(); }
	
	public synchronized void addProject(Project project) {
		projectRepository.save(project);
	}

	public Project getProjectById(Long id) {
		return projectRepository.findOne(id);
	}

	public void updateProject(Project project) {
		projectRepository.save(project);
	}

	public void deleteProject(Long id) {
		projectRepository.delete(id);
	}
}
