package com.moi.service;

import com.google.common.base.Optional;
import com.moi.entity.Employee;
import com.moi.errors.exceptions.EmptyFieldException;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;
import com.moi.repository.EmployeeRepository;
import com.moi.util.FieldChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import com.moi.util.FieldCheckerImpl;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final FieldChecker fieldChecker;

    private static final int PESEL_LENGTH = 11;
    private static final int MIN_PHONE_LENGTH = 9;
    private static final int MAX_PHONE_LENGTH = 12;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, FieldCheckerImpl fieldChecker) {
        this.employeeRepository = employeeRepository;
        this.fieldChecker = fieldChecker;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public synchronized void addEmployee(Employee employee) throws ObjectAlreadyExistException, EmptyFieldException {
        if (!employeeExist(employee)) {
            if(fieldCheck(employee)){
                employeeRepository.save(employee);
            }else{
                throw new EmptyFieldException("Wypełnij prawidłowo wszystkie pola");
            }
        } else {
            throw new ObjectAlreadyExistException("Pracownik o podanych danych już istnieje");
        }
    }

    public Employee getEmployeeById(Long id) throws ObjectNotFoundException {
        Optional<Employee> tempEmployee = Optional.fromNullable(employeeRepository.findOne(id));

        if(tempEmployee.isPresent()){
            return tempEmployee.get();
        }else{
            throw new ObjectNotFoundException("Nie znaleziono pracownika o podanym Id");
        }
    }

    public void updateEmployee(Employee employee, Long id) throws ObjectNotFoundException, EmptyFieldException {

        Optional<Employee> tempEmployee = Optional.fromNullable(employeeRepository.findOne(id));

        if(tempEmployee.isPresent()){
            if(fieldCheck(employee)){
                tempEmployee.get().setId(id);
                employeeRepository.save(tempEmployee.get());
            }else{
                throw new EmptyFieldException("Wypełnij prawidłowo wszystkie pola");
            }
        }else{
            throw new ObjectNotFoundException("Nie znaleziono pracownika o podanym Id");
        }
    }

    public void deleteEmployee(Long id) throws ObjectNotFoundException, ObjectDeletingException {

        Optional<Employee> tempEmployee = Optional.fromNullable(employeeRepository.findOne(id));

        if(tempEmployee.isPresent()){
            if(employeeRepository.findOne(id).getProjectsHasEmployees().isEmpty()){
                employeeRepository.delete(id);
            }else{
                throw new ObjectDeletingException("Klient posiada przypisane projekty.");
            }
        }else{
            throw new ObjectNotFoundException("Nie znaleziono klienta o podanym Id");
        }
    }

    private boolean employeeExist(Employee employee) {
        boolean isExist = false;

        for (Employee temp : employeeRepository.findAll()) {
            if (temp.getName().equalsIgnoreCase(employee.getName()) && temp.getLastName().equalsIgnoreCase(employee.getLastName())) {
                isExist = true;
            }
        }
        return isExist;
    }

    private boolean fieldCheck(Employee employee){
        if (!fieldChecker.checkLengthEqual(employee.getPesel(), PESEL_LENGTH)){
            return false;
        }
        if (employee.getPhone() != null && !fieldChecker.checkLength(employee.getPhone(), MIN_PHONE_LENGTH, MAX_PHONE_LENGTH)){
            return false;
        }

        if(!fieldChecker.checkIfEmpty(employee.getToVerification())){
            return false;
        }

        return true;
    }
}

