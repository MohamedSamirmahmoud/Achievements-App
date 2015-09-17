package com.ibm.achievements.services;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.ibm.achievements.model.Employee;

@Path("/EmployeeQuery")
public class EmployeeQuering {
	@GET
	@Path("/getAllEmployee")
	@Produces(MediaType.APPLICATION_JSON)
	public String hello(@PathParam("test") String test) {
		String PERSISTENCE_UNIT_NAME = "Achievements-App";
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createQuery("SELECT e from Employee e");
		List<Employee> employees = query.getResultList();
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(employees);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Path("/EmployeesInTheSameTeam")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEmployeesInTheSameTeam(
			@FormParam("employeeId") String employeeId) {
		String PERSISTENCE_UNIT_NAME = "Achievements-App";
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager
				.createQuery("SELECT e FROM Employee e where e.manager.employeeId = (SELECT E.manager.employeeId from Employee E where E.employeeId=:employeeId1) AND e.employeeId <> :employeeId2");
		query.setParameter("employeeId1", Integer.valueOf(employeeId));
		query.setParameter("employeeId2", Integer.valueOf(employeeId));
		List<Employee> employees = query.getResultList();
		ObjectMapper mapper = new ObjectMapper() ; 
		try {
			return mapper.writeValueAsString(employees);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
