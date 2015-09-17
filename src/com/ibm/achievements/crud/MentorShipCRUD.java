package com.ibm.achievements.crud;
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
import com.ibm.achievements.model.Mentorship;

public class MentorShipCRUD extends AchievementCRUD {

	@Override
	public boolean addAchievement(Achievement achievement,
			String achievementJson) {
		try {
			JSONObject jsonObject = new JSONObject(achievementJson);
			Mentorship mentorship = new Mentorship();
			mentorship.setAchievement(achievement);
			mentorship.setAchievementId(achievement.getAchievementId());
			mentorship.setTypeOfMentorship(jsonObject.get("typeOfMentorship")
					.toString());

			final String PERSISTENT_UNIT_NAME = "Achievements-App";
			final EntityManagerFactory factory = Persistence
					.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(mentorship);
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

		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em
				.createQuery("UPDATE Mentorship a SET a.TypeOfMentorShip= :type, a.achievement= :achiev where a.achievementId= :achievementId" );
		try {
			query.setParameter("type", achievementJson.get("TypeOfMentorShip")
					.toString());
			query.setParameter("achiev", achievementJson.get("achievement")
					.toString());
	 		query.setParameter("achievementId", Integer.valueOf(achievementJson.get("achievementId").toString()));

			query.executeUpdate();
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			em.close();
			return false;
		}

	}

	@Override
	public String getAchievement(Achievement achievement) {
		// TODO Auto-generated method stub
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager
				.createQuery("SELECT m  FROM MentorShip m WHERE m.achievementId=:achievementId");
		query.setParameter("achievementid", achievement.getAchievementId());
		Mentorship mentorship = (Mentorship) query.getSingleResult();
		mentorship.setAchievement(achievement);
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper= new ObjectMapper();
		try {
			  return mapper.writeValueAsString(mentorship) ;
		 }catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
				
		return null;
	}

}
