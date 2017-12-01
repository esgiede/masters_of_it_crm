package com.moi.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moi.entity.Project;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public List<Project> getAllProjects() {
		List<Project> list = new LinkedList<>();
		for(Project prj : projectRepository.findAll())
		{
			list.add(prj);
		}
		return list;
	}
	
	public synchronized void addProject(Project project) {
		projectRepository.save(project);
	}

	public Project getProjectById(Long projectId) {
		return projectRepository.findOne(projectId);
	}

	public void updateProject(Project project) {
		projectRepository.save(project);
	}

	public void deleteProject(Long projectId) {
		projectRepository.delete(projectId);
	}
}
