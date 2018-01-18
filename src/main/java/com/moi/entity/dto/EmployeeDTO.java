package com.moi.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class EmployeeDTO {
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String pesel;
    @NotNull
    private String address;
    private String phone;
    @NotNull
    private String typeOfContract;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate employedSince;

    private EmployeeDTO() {

    }

    private EmployeeDTO(Builder builder) {
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.pesel = builder.pesel;
        this.address = builder.address;
        this.phone = builder.phone;
        this.typeOfContract = builder.typeOfContract;
        this.employedSince = builder.employedSince;
    }

    public static class Builder {

        private String name;
        private String lastName;
        private String pesel;
        private String address;
        private String phone;
        private String typeOfContract;
        private LocalDate employedSince;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder pesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder typeOfContract(String typeOfContract) {
            this.typeOfContract = typeOfContract;
            return this;
        }

        public Builder employedSince(LocalDate employedSince) {
            this.employedSince = employedSince;
            return this;
        }

        public EmployeeDTO build() {
            return new EmployeeDTO(this);
        }

    }


}
