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
    private ProjectsHasEmployeesId pk = new ProjectsHasEmployeesId();
    @Column(name = "role")
    private String role;

    public ProjectsHasEmployees() {

    }

    @Transient
    public Project getProject(){
        return getPk().getProject();
    }

    public void setProject(Project project){
        getPk().setProject(project);
    }

    @Transient
    public Employee getEmployee(){ return getPk().getEmployee(); }

    public void setEmployee(Employee employee){
        getPk().setEmployee(employee);
    }

    @JsonIgnore
    public Long getProjectId(){
        return pk.getProject().getId();
    }

    @JsonIgnore
    public Long getEmployeeId(){
        return pk.getEmployee().getId();
    }
}
