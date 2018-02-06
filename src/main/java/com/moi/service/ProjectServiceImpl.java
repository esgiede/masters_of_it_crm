package com.moi.service;

import com.google.common.base.Optional;
import com.moi.entity.Project;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;
import com.moi.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Page<Project> getAllProjectsByPage(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public synchronized void addProject(Project project) throws ObjectAlreadyExistException {
        if (!projectExist(project)) {
            projectRepository.save(project);
        } else {
            throw new ObjectAlreadyExistException("Projekt o podanej nazwie już istnieje");
        }

    }

    public Project getProjectById(Long id) throws ObjectNotFoundException {
        Optional<Project> tempProject = Optional.fromNullable(projectRepository.findOne(id));

        if(tempProject.isPresent()){
            return tempProject.get();
        }else{
            throw new ObjectNotFoundException("Nie znaleziono projektu o podanym Id");
        }
    }

    public void updateProject(Project project, Long id) throws ObjectNotFoundException {
        Optional<Project> tempProject = Optional.fromNullable(projectRepository.findOne(id));

        if(tempProject.isPresent()){
            tempProject.get().setId(id);
            projectRepository.save(tempProject.get());
        }else{
            throw new ObjectNotFoundException("Nie znaleziono projektu o podanym Id");
        }
    }

    public void deleteProject(Long id) throws ObjectNotFoundException, ObjectDeletingException {
        Optional<Project> tempProject = Optional.fromNullable(projectRepository.findOne(id));

        if(tempProject.isPresent()){
            projectRepository.delete(id);
        }else{
            throw new ObjectNotFoundException("Nie znaleziono projektu o podanym Id");
        }
    }

    public boolean projectExist(Project project) {
        boolean isExist = false;

        for (Project temp : projectRepository.findAll()) {
            if (temp.getName().equalsIgnoreCase(project.getName())) {
                isExist = true;
            }
        }
        return isExist;
    }
}
