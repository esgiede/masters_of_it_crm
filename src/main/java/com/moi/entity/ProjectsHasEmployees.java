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
@Table(name = "projects_has_employees")
public class ProjectsHasEmployees implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int pheId;
	private Project project;
	private Employee employee;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "phe_id")
	public int getPheId() {
		return pheId;
	}
	public void setPheId(int pheId) {
		this.pheId = pheId;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "project_id")
	@JsonIgnoreProperties("phe")
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "employee_id")
	@JsonIgnoreProperties("phe")
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
}
