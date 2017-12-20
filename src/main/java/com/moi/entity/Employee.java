package com.moi.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private Long id;
	@NotNull
	@Column(name = "name")
	private String name;
	@NotNull
	@Column(name = "last_name")
	private String lastName;
	@NotNull
	@Column(name = "role")
	private String role;

	private Employee(){

	}

	private Employee(Builder builder){
		this.id = builder.id;
		this.name = builder.name;
		this.lastName = builder.lastName;
		this.role = builder.role;
	}
	public static class Builder{

		private Long id;
		private String name;
		private String lastName;
		private String role;


		public Builder id(Long id){
			this.id = id;
			return this;
		}

		public Builder name(String name){
			this.name = name;
			return this;
		}

		public Builder lastName(String lastName){
			this.lastName = lastName;
			return this;
		}

		public Builder role(String role){
			this.role = role;
			return this;
		}

		public Employee build(){
			return new Employee(this);
		}

	}


}
