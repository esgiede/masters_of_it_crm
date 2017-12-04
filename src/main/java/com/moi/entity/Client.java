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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table(name="clients")
@Data
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "client_id")
	private Long clientId;
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

}