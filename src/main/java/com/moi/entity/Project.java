package com.moi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "projects")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	private int projectId;
	private String name;
	private Date startDate;
	private Date endDate;
	
	private Set<ProjectsHasEmployees> phe = new HashSet<ProjectsHasEmployees>();
	private Set<ClientsHasProjects> chp = new HashSet<ClientsHasProjects>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@OneToMany(mappedBy = "project")
	@JsonIgnoreProperties("project")
	public Set<ProjectsHasEmployees> getPhe() {
		return phe;
	}
	public void setPhe(Set<ProjectsHasEmployees> phe) {
		this.phe = phe;
	}
	@OneToMany(mappedBy = "project")
	@JsonIgnoreProperties("project")
	public Set<ClientsHasProjects> getChp() {
		return chp;
	}
	public void setChp(Set<ClientsHasProjects> chp) {
		this.chp = chp;
	}
	
	
	
	
	
}
