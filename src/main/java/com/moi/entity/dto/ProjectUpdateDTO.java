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
}
