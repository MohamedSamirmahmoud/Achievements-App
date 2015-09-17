package com.ibm.achievements.services;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.crud.AchievementCRUD;
import com.ibm.achievements.model.Achievement;

@Path("/AchievementManipulation")
public class AchievementManipulation {
	@POST
	@Path("/DeleteRejectAchievements")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteRejectAchievements(
			@FormParam("achievementId") int achievementId) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("UPDATE Achievement A SET A.markedAsDeleted  = 1 WHERE A.achievementId =:achievementId");
         q.setParameter("achievementId", achievementId);
		JSONObject obj = new JSONObject();
		try {
			obj.put("status", "ok");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}

	@POST
	@Path("/AddAchievement")
	@Produces(MediaType.APPLICATION_JSON)
	public String addAchievement(
			@FormParam("achievementJson") String achievementJson) {
		Achievement achievement = AchievementCRUD
				.addBaseAchievement(achievementJson);
		JSONObject jsonObject = new JSONObject();
		try {
			Class classDefinition = Class.forName("com.ibm.achievements.dao."
					+ achievement.getAchievementType() + "CRUD");
			AchievementCRUD achievementCRUD = (AchievementCRUD) classDefinition
					.newInstance();
			if (achievementCRUD.addAchievement(achievement, achievementJson)) {
				jsonObject.put("Status", "ok");
			} else {
				jsonObject.put("Status", "failed");
			}
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	@POST
	@Path("/EditAchievement")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAchievement(
			@FormParam("achievementJson") String achievementJson) {
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonObject2 = new JSONObject(achievementJson);
			
			Class clasDefinition = Class.forName("com.ibm.achievements.dao."
					+ jsonObject2.get("type").toString() + "CRUD");
			AchievementCRUD achievementCRUD = (AchievementCRUD) clasDefinition
					.newInstance();
			if (AchievementCRUD.updateBaseAchievement(jsonObject2)&&achievementCRUD.updateAchievement(jsonObject2)) {
				jsonObject.put("Status", "ok");
			} else {
				jsonObject.put("Status", "failed");
			}
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

}
