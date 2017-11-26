package com.moi.dao;

import java.util.List;

import com.moi.entity.Project;

public interface ProjectDAO {
	List<Project> getAllProjects();
	void addProject(Project project);
}
