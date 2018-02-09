package com.moi.service;

import com.google.common.base.Optional;
import com.moi.entity.Employee;
import com.moi.errors.exceptions.EmptyFieldException;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;
import com.moi.repository.EmployeeRepository;
import javassist.bytecode.stackmap.TypeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.moi.util.FieldChecker;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final FieldChecker fieldChecker = FieldChecker.getInstance();

    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public synchronized void addEmployee(Employee employee) throws ObjectAlreadyExistException, EmptyFieldException {
        if (!employeeExist(employee)) {
            fieldChecker.checkEmployee(employee);
            employeeRepository.save(employee);
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
                tempEmployee.get().setId(id);
                employeeRepository.save(tempEmployee.get());

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
}

