package com.moi.dao;

import java.util.List;

import com.moi.entity.ClientsHasProjects;

public interface ClientsHasProjectsDAO {
	List<ClientsHasProjects> getAllChp();
	void addClientsHasProjects(ClientsHasProjects chp);
}
