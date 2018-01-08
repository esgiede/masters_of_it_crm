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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="clients")
@Getter
@Setter
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "client_id")
	private Long id;
	@NotNull
	@Column(name = "name")
	private String name;
	@NotNull
	@Column(name = "address")
	private String address;
	@NotNull
	@Column(name = "contact")
	private String contact;
	@NotNull
	@Size(min = 9, max = 12)
	@Column(name = "phone")
	private String phone;
	@OneToMany(mappedBy = "client")
	@JsonIgnore
	private Set<Project> project = new HashSet<>();

	private Client(){

	}

	private Client(Builder builder){
		id = builder.id;
		name = builder.name;
		address = builder.address;
		contact = builder.contact;
		phone = builder.phone;
	}

	public static class Builder{

		private Long id;
		private String name;
		private String address;
		private String contact;
		private String phone;

		public Builder id(Long id){
			this.id = id;
			return this;
		}

		public Builder name(String name){
			this.name = name;
			return this;
		}

		public Builder address(String address){
			this.address = address;
			return this;
		}

		public Builder contact(String contact){
			this.contact = contact;
			return this;
		}

		public Builder phone(String phone){
			this.phone = phone;
			return this;
		}

		public Client build(){
			return new Client(this);
		}

	}

}