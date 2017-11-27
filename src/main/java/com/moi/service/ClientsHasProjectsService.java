package com.moi.service;

import java.util.List;

import com.moi.entity.ClientsHasProjects;



public interface ClientsHasProjectsService {

	List<ClientsHasProjects> getAllChp();
	ClientsHasProjects getChpById(int chpId);
	boolean addClientsHasProjects(ClientsHasProjects chp);
	void updateClientsHasProjects(ClientsHasProjects chp);
	void deleteClientsHasProjects(int chpId);
	
}
