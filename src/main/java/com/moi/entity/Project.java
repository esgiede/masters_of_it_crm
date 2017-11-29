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
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "projects")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	@Getter @Setter private int projectId;
	@Column(name = "name")
	@Getter @Setter private String name;
	@Column(name = "start_date")
	@Getter @Setter private Date startDate;
	@Column(name = "end_date")
	@Getter @Setter private Date endDate;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "client_id", insertable = false, updatable = false)
	@JsonIgnoreProperties("project")
	@Getter @Setter private Client client;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	@JsonIgnoreProperties("project")
	@Getter @Setter private Employee employee;
	@Column(name = "client_id")
	@Getter @Setter private int clientId;
	@Column(name = "employee_id")
	@Getter @Setter private int employeeId;

	
	
	
	
	
	
}
