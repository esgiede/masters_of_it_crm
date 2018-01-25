package com.moi.service;

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
        if (employeeRepository.exists(id)) {
            return employeeRepository.findOne(id);
        } else {
            throw new ObjectNotFoundException("Nie znaleziono pracownika o podanym Id");
        }
    }

    public void updateEmployee(Employee employee, Long id) throws ObjectNotFoundException, EmptyFieldException {
        if (employeeRepository.exists(id)) {
            if (fieldCheck(employee)){
                employee.setId(id);
                employeeRepository.save(employee);
            }else{
                throw new EmptyFieldException("Wypełnij prawidłowo wszystkie pola");
            }

        } else {
            throw new ObjectNotFoundException("Nie znaleziono pracownika o podanym Id");
        }
    }

    public void deleteEmployee(Long id) throws ObjectNotFoundException, ObjectDeletingException {
        if (employeeRepository.exists(id)) {
            if (employeeRepository.findOne(id).getProjectsHasEmployees().isEmpty()) {
                employeeRepository.delete(id);
            } else {
                throw new ObjectDeletingException("Klient posiada przypisane projekty.");
            }
        } else {
            throw new ObjectNotFoundException("Nie znaleziono klienta o podanym Id");
        }
    }

    public boolean employeeExist(Employee employee) {
        boolean isExist = false;

        for (Employee temp : employeeRepository.findAll()) {
            if (temp.getName().equalsIgnoreCase(employee.getName()) && temp.getLastName().equalsIgnoreCase(employee.getLastName())) {
                isExist = true;
            }
        }
        return isExist;
    }

    public boolean fieldCheck(Employee employee){
        if(!fieldChecker.checkIfEmpty(employee.getName())){
            return false;
        }
        if (!fieldChecker.checkIfEmpty(employee.getLastName())){
            return false;
        }
        if (!fieldChecker.checkIfEmpty(employee.getAddress())){
            return false;
        }
        if (!fieldChecker.checkIfEmpty(employee.getPesel())){
            return false;
        }
        if (!fieldChecker.checkIfEmpty(employee.getTypeOfContract())){
            return false;
        }
        if (!fieldChecker.checkLengthEqual(employee.getPesel(), PESEL_LENGTH)){
            return false;
        }
        if (employee.getPhone() != null && !fieldChecker.checkLength(employee.getPhone(), MIN_PHONE_LENGTH, MAX_PHONE_LENGTH)){
            return false;
        }
        return true;
    }
}

