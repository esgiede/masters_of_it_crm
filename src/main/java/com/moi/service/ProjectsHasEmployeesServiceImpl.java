package com.moi.service;

import com.moi.entity.ProjectsHasEmployees;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
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
    public ProjectsHasEmployees getPheById(Long id) {
        return projectsHasEmployeesRepository.findOne(id);
    }

    @Override
    public synchronized void addPhe(ProjectsHasEmployees phe) throws ObjectAlreadyExistException {
        if(!pheExist(phe)){
            projectsHasEmployeesRepository.save(phe);
        }else{
            throw new ObjectAlreadyExistException("Wpis już istnieje");
        }
    }

    @Override
    public void updatePhe(ProjectsHasEmployees phe, Long id) {
        phe.setId(id);
        projectsHasEmployeesRepository.save(phe);
    }

    @Override
    public void deletePhe(Long id) {
        projectsHasEmployeesRepository.delete(id);
    }

    @Override
    public boolean pheExist(ProjectsHasEmployees phe) {

        for(ProjectsHasEmployees temp : projectsHasEmployeesRepository.findAll()){
            if(Objects.equals(temp.getProjectId(), phe.getProjectId()) && Objects.equals(temp.getEmployeeId(), phe.getEmployeeId())){
                return true;
            }
        }
        return false;
    }
}
