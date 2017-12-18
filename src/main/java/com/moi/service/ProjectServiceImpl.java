package com.moi.service;

import java.util.List;

import com.moi.errors.exceptions.ObjectNotFoundException;
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
	public synchronized void addProject(Project project) { projectRepository.save(project); }
	public Project getProjectById(Long id) throws ObjectNotFoundException {
		if(projectRepository.exists(id) == true){
			return projectRepository.findOne(id);
		}else{
			throw new ObjectNotFoundException("Nie znaleziono projektu o podanym Id");
		}
	}
	public void updateProject(Project project, Long id) throws ObjectNotFoundException {
		if(projectRepository.exists(id)){
			project.setId(id);
			projectRepository.save(project);
		}else{
			throw new ObjectNotFoundException("Nie znaleziono projektu o podanym Id");
		}
	}
	public void deleteProject(Long id) throws ObjectNotFoundException {
		if(projectRepository.exists(id) == true){
			projectRepository.delete(id);
		}else{
			throw new ObjectNotFoundException("Nie znaleziono projektu o podanym Id");
		}
	}
}
