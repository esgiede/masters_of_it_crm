package com.moi.entity.dto;

import com.moi.entity.Employee;
import com.moi.entity.Project;
import lombok.Data;

@Data
public class ProjectsHasEmployeesDTO {

    private String role;
    private Project project;
    private Employee employee;

    private ProjectsHasEmployeesDTO(){

    }

    private ProjectsHasEmployeesDTO(Builder builder){
        this.role = builder.role;
        this.employee = builder.employee;
        this.project = builder.project;
    }

    public static class Builder{

        private String role;
        private Project project;
        private Employee employee;

        public Builder role(String role){
            this.role = role;
            return this;
        }

        public Builder project(Project project){
            this.project = project;
            return this;
        }

        public Builder employee(Employee employee){
            this.employee = employee;
            return this;
        }

        public ProjectsHasEmployeesDTO build(){
            return new ProjectsHasEmployeesDTO(this);
        }

    }

}
