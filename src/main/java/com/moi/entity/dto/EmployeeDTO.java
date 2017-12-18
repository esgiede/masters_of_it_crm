package com.moi.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployeeDTO {
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String role;

    private EmployeeDTO(){

    }

    private EmployeeDTO(Builder builder){
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.role = builder.role;
    }
    public static class Builder{

        private String name;
        private String lastName;
        private String role;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder role(String role){
            this.role = role;
            return this;
        }

        public EmployeeDTO build(){
            return new EmployeeDTO(this);
        }

    }


}
