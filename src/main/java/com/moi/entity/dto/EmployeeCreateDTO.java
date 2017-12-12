package com.moi.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployeeCreateDTO {
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String role;
}
