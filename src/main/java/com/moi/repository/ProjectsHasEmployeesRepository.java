package com.moi.repository;

import com.moi.entity.ProjectsHasEmployees;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectsHasEmployeesRepository extends CrudRepository<ProjectsHasEmployees, Long> {
    List<ProjectsHasEmployees> findAll();
}
