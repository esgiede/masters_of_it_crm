package com.moi.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Getter
@Setter
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	private Long id;
	@NotNull
	@Column(name = "name")
	private String name;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_date")
	private LocalDate startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_date")
	private LocalDate endDate;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	private Client client;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.project")
	@JsonIgnore
	private Set<ProjectsHasEmployees> projectsHasEmployees = new HashSet<>();

	private Project(){

	}

	private Project(Builder builder){
		this.id = builder.id;
		this.name = builder.name;
		this.startDate = builder.startDate;
		this.endDate = builder.endDate;
		this.client = builder.client;
	}

	public static class Builder{

		private Long id;
		private String name;
		private LocalDate startDate;
		private LocalDate endDate;
		private Set<Employee> employees = new HashSet<>();
		private Client client;

		public Builder id(Long id){
			this.id = id;
			return this;
		}

		public Builder name(String name){
			this.name = name;
			return this;
		}

		public Builder startDate(LocalDate startDate){
			this.startDate = startDate;
			return this;
		}

		public Builder endDate(LocalDate endDate){
			this.endDate = endDate;
			return this;
		}

		public Builder employees(Set<Employee> employees){
			this.employees = employees;
			return this;
		}

		public Builder client(Client client){
			this.client = client;
			return this;
		}

		public Project build(){
			return new Project(this);
		}

	}


	
}
