package com.moi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "projects_has_employees")
@AssociationOverrides({
        @AssociationOverride(name = "pk.employee",
                joinColumns = @JoinColumn(name = "employee_id")),
        @AssociationOverride(name = "pk.project",
                joinColumns = @JoinColumn(name = "project_id"))})
@Getter
@Setter
public class ProjectsHasEmployees implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "phe_id")
    private Long id;

    @JsonIgnore
    private ProjectsHasEmployeesId pk;
    @Column(name = "role")
    private String role;

    private ProjectsHasEmployees() {

    }

    private ProjectsHasEmployees(Builder builder) {
        this.id = builder.id;
        this.pk = builder.pk;
        this.role = builder.role;
    }

    @Transient
    public Project getProject() {
        return getPk().getProject();
    }

    public void setProject(Project project) {
        getPk().setProject(project);
    }

    @Transient
    public Employee getEmployee() {
        return getPk().getEmployee();
    }

    public void setEmployee(Employee employee) {
        getPk().setEmployee(employee);
    }

    @JsonIgnore
    public Long getProjectId() {
        return pk.getProject().getId();
    }

    @JsonIgnore
    public Long getEmployeeId() {
        return pk.getEmployee().getId();
    }

    public static class Builder {

        private Long id;
        private ProjectsHasEmployeesId pk;
        private String role;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder pk(ProjectsHasEmployeesId pk) {
            this.pk = pk;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public ProjectsHasEmployees build() {
            return new ProjectsHasEmployees(this);
        }

    }
}
