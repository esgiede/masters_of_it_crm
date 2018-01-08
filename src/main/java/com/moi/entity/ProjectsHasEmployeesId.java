package com.moi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ProjectsHasEmployeesId implements Serializable {

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Project project;

}
