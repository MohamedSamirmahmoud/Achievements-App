package com.ibm.achievements.crud;
/**
 * Ahmed Ayman
 */

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.Achievement;
import com.ibm.achievements.model.HighImpactAsset;
import com.ibm.achievements.model.SuccessStory;

public class SuccessStoriesCRUD extends AchievementCRUD {

	@Override
	public boolean addAchievement(Achievement achievement,
			String achievementJson) {

		try {
			JSONObject jsonObject = new JSONObject(achievementJson);
			SuccessStory successStory = new SuccessStory();
			successStory.setAchievement(achievement);
			successStory.setAchievementId(achievement.getAchievementId());
			successStory.setBusUnit(jsonObject.get("busUnit").toString());
			successStory.setCountry(jsonObject.get("country").toString());
			successStory.setEngagementName(jsonObject.get("engagementName")
					.toString());
			successStory.setSuccessStoryLink(jsonObject.get("successStoryLink")
					.toString());
			successStory.setType(jsonObject.get("successStoriesType").toString());
			successStory.setCustomerName(jsonObject.get("customerName")
					.toString());
			final String PERSISTENT_UNIT_NAME = "Achievements-App";
			final EntityManagerFactory factory = Persistence
					.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(successStory);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAchievement(JSONObject achievementJson) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonObject = new JSONObject(achievementJson);
			final String PERSISTENT_UNIT_NAME = "Achievements-App";
			final EntityManagerFactory factory = Persistence
					.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			Query query = entityManager
					.createQuery("UPDATE SuccessStory Ss SET Ss.busUnit=:busUnit, Ss.country=:country, Ss.engagementName=:engagementName, Ss.successStoryLink=:successStoryLink, Ss.type=:type  where Ss.achievementId= :achievementId");
			SuccessStory successStory = new SuccessStory();
			successStory.setBusUnit(jsonObject.get("busUnit").toString());
			successStory.setCountry(jsonObject.get("country").toString());
			successStory.setEngagementName(jsonObject.get("engagementName")
					.toString());
			successStory.setSuccessStoryLink(jsonObject.get("successStoryLink")
					.toString());
			successStory.setType(jsonObject.get("type").toString());
			query.setParameter("achievementId",
					Integer.valueOf(achievementJson.get("achievementId").toString()));
			query.executeUpdate();
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getAchievement(Achievement achievement) {
		// TODO Auto-generated method stub
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager
				.createQuery("SELECT S FROM SuccessStory S WHERE S.achievementId=:achievementId");
		query.setParameter("achievementid", achievement.getAchievementId());

		SuccessStory successStory = (SuccessStory) query.getSingleResult();
		successStory.setAchievement(achievement);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper
					.writeValueAsString(successStory) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}