package com.ibm.achievements.services;

/**
 * Ahmed Ayman
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/AchievmentStatus")
public class AchievmentStatus {
	@GET
	@Path("/{achievementId}/{achievementStatus}")
	@Produces(MediaType.TEXT_PLAIN)
	public String achievmentStatus(
			@PathParam("achievementStatus") String achievementStatus,
			@PathParam("achievementId") int achievementId) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entitymanager = entityManagerFactory
				.createEntityManager();
		JSONObject jsonObject = new JSONObject();
		Query query = entitymanager
				.createQuery("UPDATE Achievement AS achievement SET achievement.status=:achievementStatus WHERE achievement.achievementId=:achievementId");
		entitymanager.getTransaction().begin();
		query.setParameter("achievementStatus", achievementStatus);
		query.setParameter("achievementId", achievementId);
		query.executeUpdate();
		entitymanager.getTransaction().commit();
		entitymanager.close();
		try {
			jsonObject.put("Status", "OK");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
}
