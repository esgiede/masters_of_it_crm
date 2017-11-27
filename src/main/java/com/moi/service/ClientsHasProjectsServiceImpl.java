package com.moi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moi.dao.ClientsHasProjectsDAO;
import com.moi.entity.ClientsHasProjects;

@Service
public class ClientsHasProjectsServiceImpl implements ClientsHasProjectsService {

	@Autowired
	private ClientsHasProjectsDAO clientsHasProjectsDAO;
	
	public List<ClientsHasProjects> getAllChp() {
		return clientsHasProjectsDAO.getAllChp();
	}

	public synchronized boolean addClientsHasProjects(ClientsHasProjects chp) {
		clientsHasProjectsDAO.addClientsHasProjects(chp);
		return true;
	}
	
}
