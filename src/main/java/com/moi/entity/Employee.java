package com.moi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "pesel")
    private String pesel;
    @NotNull
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @NotNull
    @Column(name = "type_of_contract")
    private String typeOfContract;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "employed_since")
    private LocalDate employedSince;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.employee")
    @JsonIgnore
    private Set<ProjectsHasEmployees> projectsHasEmployees = new HashSet<>();

    private Employee() {

    }

    private Employee(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.pesel = builder.pesel;
        this.address = builder.address;
        this.phone = builder.phone;
        this.typeOfContract = builder.typeOfContract;
        this.employedSince = builder.employedSince;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String lastName;
        private String pesel;
        private String address;
        private String phone;
        private String typeOfContract;
        private LocalDate employedSince;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder pesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder typeOfContract(String typeOfContract) {
            this.typeOfContract = typeOfContract;
            return this;
        }

        public Builder employedSince(LocalDate employedSince) {
            this.employedSince = employedSince;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }

    }


}
