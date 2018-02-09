package com.moi.util;

import com.moi.entity.Client;
import com.moi.entity.Employee;
import com.moi.entity.Project;
import com.moi.errors.exceptions.EmptyFieldException;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkArgument;

@Component
public class FieldChecker {

    private static FieldChecker instance = null;

    private static final int PESEL_LENGTH = 11;
    private static final int MIN_PHONE_LENGTH = 9;
    private static final int MAX_PHONE_LENGTH = 12;

    private FieldChecker(){}

    public static FieldChecker getInstance(){
        if(instance == null) {
            instance = new FieldChecker();
        }
        return instance;
    }

    public void checkEmployee(Employee employee) throws EmptyFieldException {
        try{
            checkArgument(employee.getPesel().length() == PESEL_LENGTH);
        }catch(IllegalArgumentException e){
            throw new EmptyFieldException("Wprowadź poprawny numer pesel");
        }
    }

    public void checkClient(Client client){
        //TODO implement checkClient method
    }

    public void checkProject(Project project){
        //TODO implement CheckProject method
    }
}
