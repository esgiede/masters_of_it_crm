package com.moi.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private Long employeeId;
	@NotNull
	@Column(name = "name")
	private String name;
	@NotNull
	@Column(name = "last_name")
	private String lastName;
	@NotNull
	@Column(name = "role")
	private String role;


}
