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
	
	private int chpID;
	private Client client;
	private Project project;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "chp_id")
	public int getChpID() {
		return chpID;
	}
	public void setChpID(int chpID) {
		this.chpID = chpID;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "client_id")
	@JsonIgnoreProperties("chp")
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "project_id")
	@JsonIgnoreProperties("chp")
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
	
}
