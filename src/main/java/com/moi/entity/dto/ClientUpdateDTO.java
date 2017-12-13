package com.moi.entity.dto;

import lombok.Data;

import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    private ClientUpdateDTO(){

    }

    private ClientUpdateDTO(Builder builder){
        id = builder.id;
        name = builder.name;
        address = builder.address;
        contact = builder.contact;
        phone = builder.phone;
    }

    public static class Builder{

        private Long id;
        private String name;
        private String address;
        private String contact;
        private String phone;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder address(String address){
            this.address = address;
            return this;
        }

        public Builder contact(String contact){
            this.contact = contact;
            return this;
        }

        public Builder phone(String phone){
            this.phone = phone;
            return this;
        }

        public ClientUpdateDTO build(){
            return new ClientUpdateDTO(this);
        }

    }
}
