package com.moi.entity;

import java.io.Serializable;
import java.util.Date;

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

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "projects")
@Data
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	private int projectId;
	@Column(name = "name")
	private String name;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "client_id", insertable = false, updatable = false)
	@JsonIgnoreProperties("project")
	private Client client;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	@JsonIgnoreProperties("project")
	private Employee employee;
	@Column(name = "client_id")
	private int clientId;
	@Column(name = "employee_id")
	private int employeeId;

	
	
	
	
	
	
}
