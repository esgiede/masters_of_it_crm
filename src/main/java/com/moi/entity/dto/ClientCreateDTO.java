package com.moi.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ClientCreateDTO {
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String contact;
    @NotNull
    @Size(min = 9, max = 12)
    private String phone;
}
