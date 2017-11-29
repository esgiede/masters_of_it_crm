package com.moi.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	@Getter @Setter private int employeeId;
	@Column(name = "name")
	@Getter @Setter private String name;
	@Column(name = "last_name")
	@Getter @Setter private String lastName;
	@Column(name = "role")
	@Getter @Setter private String role;
	@OneToMany(mappedBy = "employee")
	@JsonIgnoreProperties("employee")
	@Getter @Setter private Set<Project> project = new HashSet<Project>();

	
	
}
