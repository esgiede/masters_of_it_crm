package com.moi.dao;

import java.util.List;

import com.moi.entity.ClientsHasProjects;

public interface ClientsHasProjectsDAO {
	List<ClientsHasProjects> getAllChp();
	ClientsHasProjects getChpById(int chpId);
	void addClientsHasProjects(ClientsHasProjects chp);
	void updateClientsHasProjects(ClientsHasProjects chp);
	void deleteClientsHasProjects(int chpId);
	boolean cphExist(int clientId, int projectsId);
}
