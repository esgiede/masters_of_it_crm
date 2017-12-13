package com.moi.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moi.entity.Client;
import com.moi.entity.Employee;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class ProjectCreateDTO {
    @NotNull
    private String name;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private ProjectCreateDTO(){

    }

    private ProjectCreateDTO(Builder builder){
        this.name = builder.name;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }

    public static class Builder{

        private String name;
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder startDate(LocalDate startDate){
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate){
            this.endDate = endDate;
            return this;
        }

        public ProjectCreateDTO build(){
            return new ProjectCreateDTO(this);
        }

    }

}
