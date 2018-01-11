package com.moi.service;

import com.moi.entity.ProjectsHasEmployees;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectNotFoundException;
import com.moi.repository.ProjectsHasEmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProjectsHasEmployeesServiceImpl implements ProjectsHasEmployeesService {

    @Autowired
    private ProjectsHasEmployeesRepository projectsHasEmployeesRepository;

    @Override
    public List<ProjectsHasEmployees> getAllPhe() {
        return projectsHasEmployeesRepository.findAll();
    }

    @Override
    public ProjectsHasEmployees getPheById(Long id) throws ObjectNotFoundException {
        if (projectsHasEmployeesRepository.exists(id)) {
            return projectsHasEmployeesRepository.findOne(id);
        } else {
            throw new ObjectNotFoundException("Nie znaleziono wpisu o podanym Id");
        }
    }

    @Override
    public synchronized void addPhe(ProjectsHasEmployees phe) throws ObjectAlreadyExistException {
        if (!pheExist(phe)) {
            projectsHasEmployeesRepository.save(phe);
        } else {
            throw new ObjectAlreadyExistException("Wpis o podanych parametrach już istnieje");
        }
    }

    @Override
    public void updatePhe(ProjectsHasEmployees phe, Long id) throws ObjectNotFoundException, ObjectAlreadyExistException {
        if (projectsHasEmployeesRepository.exists(id)) {
            if (!pheExist(phe)) {
                phe.setId(id);
                projectsHasEmployeesRepository.save(phe);
            } else {
                throw new ObjectAlreadyExistException("Wpis o podanych parametrach już istnieje");
            }
        } else {
            throw new ObjectNotFoundException("Nie znaleziono wpisu o podanym id");
        }
    }

    @Override
    public void deletePhe(Long id) throws ObjectNotFoundException {
        if (projectsHasEmployeesRepository.exists(id)) {
            projectsHasEmployeesRepository.delete(id);
        } else {
            throw new ObjectNotFoundException("Wpis o podanym id nie istnieje");
        }
    }

    @Override
    public boolean pheExist(ProjectsHasEmployees phe) {

        for (ProjectsHasEmployees temp : projectsHasEmployeesRepository.findAll()) {

            if (Objects.equals(temp.getProjectId(), phe.getProjectId()) && Objects.equals(temp.getEmployeeId(), phe.getEmployeeId())) {
                return true;
            }
        }
        return false;
    }
}
