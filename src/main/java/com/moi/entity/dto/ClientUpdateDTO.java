package com.moi.entity.dto;

import com.moi.entity.Project;
import lombok.Data;

import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
public class ClientUpdateDTO {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String contact;
    @NotNull
    @Size(min = 9, max = 12)
    private String phone;
    private Set<Project> project = new HashSet<>();
}
