package com.ibm.achievements.services;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.Achievement;
import com.ibm.achievements.model.Employee;
import com.ibm.achievements.model.SuccessStory;

@Path("/AchievementShare")
public class AchievementShare {
	@GET
	@Path("/{employeeId}/{achievementId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String achievmentShare(
			@PathParam("achievementId") int achievementId,
			@PathParam("employeeId") int employeeId) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = entityManagerFactory
				.createEntityManager();
		entitymanager.getTransaction().begin();
		Employee employee = entitymanager.find(Employee.class, employeeId);
		Achievement achievement = entitymanager.find(Achievement.class,
				achievementId);
		employee.getAchievements().add(achievement);
		achievement.getEmployees().add(employee);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", "OK");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}