package com.moi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity
@Table(name = "projects")
@Data
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	private Long projectId;
	@Column(name = "name")
	private String name;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "client_id", insertable = false, updatable = false)
	@JsonBackReference(value = "client")
	private Client client;
	@Column(name = "client_id")
	private int clientId;
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "projects_has_employees",
			joinColumns = { @JoinColumn(name = "project_id")},
			inverseJoinColumns = { @JoinColumn(name = "employee_id" )}
	)
	Set<Employee> employees = new HashSet<>();

	
	
	
	
	
	
}
