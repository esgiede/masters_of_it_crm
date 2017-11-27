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

	public Employee getEmployeeById(int employeeId) {
		return entityManager.find(Employee.class, employeeId);
	}

	public void UpdateEmployee(Employee employee) {
		Employee empl = getEmployeeById(employee.getEmployeeId());
		empl.setName(employee.getName());
		empl.setLastName(employee.getLastName());
		empl.setRole(employee.getRole());
		entityManager.flush();
	}

	public void deleteEmployee(int employeeId) {
		entityManager.remove(getEmployeeById(employeeId));
	}

	public boolean employeeExist(String name, String lastName) {
		String hql = "FROM Employee as empl WHERE empl.name = ? and empl.category";
		int count = entityManager.createQuery(hql).setParameter(1, name).setParameter(2, lastName).getResultList().size();
		return count > 0 ? true : false;
	}
	
}
