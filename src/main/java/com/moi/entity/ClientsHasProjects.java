package com.moi.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "clients_has_projects")
public class ClientsHasProjects implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int chpId;
	private int projectId;
	private int clientId;
	private Client client;
	private Project project;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "chp_id")
	public int getChpId() {
		return chpId;
	}
	public void setChpId(int chpId) {
		this.chpId = chpId;
	}
	@Column(name = "project_id")
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	@Column(name = "client_id")
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "client_id", insertable = false, updatable = false)
	@JsonIgnoreProperties("chp")
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "project_id", insertable = false, updatable = false)
	@JsonIgnoreProperties("chp")
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
	
}
