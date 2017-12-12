package com.moi.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployeeUpdateDTO {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String role;
}
