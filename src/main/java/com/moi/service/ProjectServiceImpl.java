package com.moi.service;

import java.util.List;

import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
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
	public synchronized void addProject(Project project) throws ObjectAlreadyExistException {
		if(!projectExist(project)){
			projectRepository.save(project);
		}else{
			throw new ObjectAlreadyExistException("Projekt o podanej nazwie już istnieje");
		}

	}
	public Project getProjectById(Long id) throws ObjectNotFoundException {
		if(projectRepository.exists(id)){
			return projectRepository.findOne(id);
		}else{
			throw new ObjectNotFoundException("Nie znaleziono projektu o podanym Id");
		}
	}
	public void updateProject(Project project, Long id) throws ObjectNotFoundException, ObjectAlreadyExistException {
		if(projectRepository.exists(id)){
				project.setId(id);
				projectRepository.save(project);
			}else{
			throw new ObjectNotFoundException("Nie znaleziono projektu o podanym Id");
		}
	}
	public void deleteProject(Long id) throws ObjectNotFoundException, ObjectDeletingException {
		/*if(projectRepository.exists(id)){
			if(projectRepository.findOne(id).getEmployees().isEmpty() && projectRepository.findOne(id).getClient() == null){
				projectRepository.delete(id);
			}else{
				throw new ObjectDeletingException("Projekt jest przypisany do klienta lub posiada przypisanych pracowników");
			}

		}else{
			throw new ObjectNotFoundException("Nie znaleziono projektu o podanym Id");
		}*/
	}
	public boolean projectExist(Project project){
		boolean isExist = false;

		for(Project temp : projectRepository.findAll()){
			if(temp.getName().equalsIgnoreCase(project.getName())){
				isExist = true;
			}
		}
		return isExist;
	}
}
