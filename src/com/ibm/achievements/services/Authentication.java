package com.ibm.achievements.services;

import java.io.IOException;
import java.util.ArrayList;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.Employee;

@Path("/Authentication")
public class Authentication {
	@POST
	@Path("/Login")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@FormParam("email") String email,
			@FormParam("password") String password) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("SELECT e FROM Employee e where e.employeeMail=:email AND e.employeePassword=:password");
		q.setParameter("email", email) ;
		q.setParameter("password", password) ;
		JSONObject jsonObject = new JSONObject() ;
		try{
		Employee e = (Employee) q.getSingleResult() ;
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Employee>employees = new ArrayList<Employee>() ;
		employees.add(e) ;
		em.close();
        return mapper.writeValueAsString(employees);		
		}catch(Exception e){
			try {
				jsonObject.put("status", "failed") ;
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return jsonObject.toString();
	}
	
}
