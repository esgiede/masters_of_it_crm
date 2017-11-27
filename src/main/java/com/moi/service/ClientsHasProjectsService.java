package com.moi.service;

import java.util.List;

import com.moi.entity.ClientsHasProjects;



public interface ClientsHasProjectsService {

	List<ClientsHasProjects> getAllChp();
	boolean addClientsHasProjects(ClientsHasProjects chp);
	
}
