package com.ibm.achievements.crud;
import java.io.IOException;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.Achievement;
import com.ibm.achievements.model.Disclosure;
import com.ibm.achievements.model.SuccessStory;

public class DisclosureCRUD extends AchievementCRUD {

	@Override
	public boolean addAchievement(Achievement achievement,
			String achievementJson) {
		try {
			JSONObject jsonObject = new JSONObject(achievementJson);
			Disclosure disclosure = new Disclosure();
			disclosure.setAchievement(achievement);
			disclosure.setNumber(Integer.valueOf(jsonObject.get("number")
					.toString()));
			disclosure.setAchievementId(achievement.getAchievementId());
			disclosure.setTitle(jsonObject.get("title").toString());
			final String PERSISTENT_UNIT_NAME = "Achievements-App";
			final EntityManagerFactory factory = Persistence
					.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(disclosure);
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
	public boolean updateAchievement(JSONObject achievementJson) {
		final String PERSISTANT_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factroy = Persistence
				.createEntityManagerFactory(PERSISTANT_UNIT_NAME);
		EntityManager entityManager = factroy.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager
				.createQuery("UPDATE Disclosure D SET D.number =:number, D.title=:title WHERE D.achievementId=:achievementId");
		try {
			query.setParameter("number", Integer.valueOf(achievementJson.get("number").toString()));
			query.setParameter("title", achievementJson.get("title").toString());
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
		final String PERSISTANT_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factroy = Persistence
				.createEntityManagerFactory(PERSISTANT_UNIT_NAME);
		EntityManager entityManager = factroy.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager
				.createQuery("SELECT d FROM Disclosure d WHERE d.achievementId=:achievementId");
		query.setParameter("achievementId", achievement.getAchievementId());
		Disclosure disclosure = (Disclosure) query.getSingleResult();
		disclosure.setAchievement(achievement);
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			return mapper
					.writeValueAsString(disclosure);
		} catch (  IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
