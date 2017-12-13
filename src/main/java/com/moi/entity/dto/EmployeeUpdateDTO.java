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

    private EmployeeUpdateDTO(){

    }

    private EmployeeUpdateDTO(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.role = builder.role;
    }
    public static class Builder{

        private Long id;
        private String name;
        private String lastName;
        private String role;


        public Builder id(Long id){
            this.id = id;
            return this;
        }

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

        public EmployeeUpdateDTO build(){
            return new EmployeeUpdateDTO(this);
        }

    }


}
