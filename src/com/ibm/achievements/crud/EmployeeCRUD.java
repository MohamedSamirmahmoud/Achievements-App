package com.ibm.achievements.crud;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ibm.achievements.model.Achievement;
import com.ibm.achievements.model.Employee;

public class EmployeeCRUD {
	public Employee getEmployeeById(int employeeId){
		final String PERSISTENCE_UNIT_NAME = "Achievements-App" ;
		final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager() ;
		Query query = entityManager.createQuery("SELECT e from Employee e where e.employeeId=:employeeIdParam") ;
		query.setParameter("employeeIdParam", employeeId) ;
		return (Employee) query.getSingleResult() ;
	}

}
