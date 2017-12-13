package com.moi.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moi.entity.Client;
import com.moi.entity.Employee;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class ProjectUpdateDTO {
    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Client client;
    Set<Employee> employees = new HashSet<>();

    private ProjectUpdateDTO(){

    }

    private ProjectUpdateDTO(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.employees = builder.employees;
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

        public ProjectUpdateDTO build(){
            return new ProjectUpdateDTO(this);
        }

    }

}
