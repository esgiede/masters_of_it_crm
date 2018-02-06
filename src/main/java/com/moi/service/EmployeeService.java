package com.moi.service;

import com.moi.entity.Employee;
import com.moi.errors.exceptions.EmptyFieldException;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id) throws ObjectNotFoundException;

    void addEmployee(Employee employee) throws ObjectAlreadyExistException, EmptyFieldException;

    void updateEmployee(Employee employee, Long id) throws ObjectNotFoundException, EmptyFieldException;

    void deleteEmployee(Long id) throws ObjectNotFoundException, ObjectDeletingException;
}
