package com.moi.dao;


import org.springframework.transaction.annotation.Transactional;

import com.moi.entity.Employee;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		String hql = "FROM Employee as empl ORDER BY empl.employeeId";
		return (List<Employee>) entityManager.createQuery(hql).getResultList();
	}
	
	public void addEmployee(Employee employee) {
		entityManager.persist(employee);
	}
	
}
