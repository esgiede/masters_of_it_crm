package com.moi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ProjectsHasEmployeesId implements Serializable {

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Project project;

    private ProjectsHasEmployeesId() {

    }

    private ProjectsHasEmployeesId(Builder builder) {
        this.employee = builder.employee;
        this.project = builder.project;
    }

    public static class Builder {

        private Employee employee;
        private Project project;

        public Builder employee(Employee employee) {
            this.employee = employee;
            return this;
        }

        public Builder project(Project project) {
            this.project = project;
            return this;
        }

        public ProjectsHasEmployeesId build() {
            return new ProjectsHasEmployeesId(this);
        }
    }

}
