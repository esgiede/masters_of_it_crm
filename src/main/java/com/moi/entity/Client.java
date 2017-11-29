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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="clients")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "client_id")
	@Getter @Setter private int clientId;
	@Column(name = "name")
	@Getter @Setter private String name;
	@Column(name = "address")
	@Getter @Setter private String address;
	@Column(name = "contact")
	@Getter @Setter private String contact;
	@Column(name = "phone")
	@Getter @Setter private int phone;
	@OneToMany(mappedBy = "client")
	@JsonIgnoreProperties("client")
	@Getter @Setter private Set<Project> project = new HashSet<Project>();
	
	
}