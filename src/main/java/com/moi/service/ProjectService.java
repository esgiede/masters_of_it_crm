package com.moi.service;

import com.moi.entity.Project;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {
    Page<Project> getAllProjectsByPage(Pageable pageable);

    Project getProjectById(Long id) throws ObjectNotFoundException;

    void addProject(Project project) throws ObjectAlreadyExistException;

    void updateProject(Project project, Long id) throws ObjectNotFoundException;

    void deleteProject(Long id) throws ObjectNotFoundException, ObjectDeletingException;

    boolean projectExist(Project project);
}
