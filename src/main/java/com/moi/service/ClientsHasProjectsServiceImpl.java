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
		if (clientsHasProjectsDAO.cphExist(chp.getClientId(), chp.getProjectId())) {
            return false;
        } else {
            clientsHasProjectsDAO.addClientsHasProjects(chp);
            return true;
        }
	}

	public ClientsHasProjects getChpById(int chpId) {
		ClientsHasProjects obj = clientsHasProjectsDAO.getChpById(chpId);
		return obj;
	}

	public void updateClientsHasProjects(ClientsHasProjects chp) {
		clientsHasProjectsDAO.updateClientsHasProjects(chp);
	}

	public void deleteClientsHasProjects(int chpId) {
		clientsHasProjectsDAO.deleteClientsHasProjects(chpId);
	}
	
}
