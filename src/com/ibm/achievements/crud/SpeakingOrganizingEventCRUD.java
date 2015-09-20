package com.ibm.achievements.crud;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.Achievement;
import com.ibm.achievements.model.SpeakingOrganizingEvent;

public class SpeakingOrganizingEventCRUD extends AchievementCRUD{

	@Override
	public boolean addAchievement(Achievement achievement,
			String achievementJson) {
		SpeakingOrganizingEvent event = new SpeakingOrganizingEvent(); 
		event.setAchievement(achievement);
		event.setAchievementId(achievement.getAchievementId());
		try {
			JSONObject jsonObject = new JSONObject(achievementJson) ;
			event.setBusinessUnits(jsonObject.getString("businessUnits"));
			event.setCountry(jsonObject.getString("country"));
			event.setSessions(jsonObject.getString("sessions"));
			event.setSessionType(jsonObject.getString("sessionType"));
			event.setSpeakingOrganizingEventsType(jsonObject.getString("speakingOrganizingEventsType"));
			event.setTitleOfConference(jsonObject.getString("titleOfConference"));
			event.setTitleOfEvent(jsonObject.getString("titleOfEvent"));
			event.setTypeOfEvent(jsonObject.getString("typeOfEvent"));
			final String PERSISTENCE_UNIT_NAME = "Achievements-App" ;
			final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME) ; 
			EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
			entityManager.getTransaction().begin(); 
			entityManager.persist(event);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true ; 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}

	@Override
	public boolean updateAchievement(JSONObject achievementJson) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App" ;
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME) ; 
		EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
		try {
			SpeakingOrganizingEvent event = entityManager.find(SpeakingOrganizingEvent.class, achievementJson.getInt("achievementId"));
		entityManager.getTransaction().begin(); 
		event.setBusinessUnits(achievementJson.getString("businessUnits"));
		event.setCountry(achievementJson.getString("country"));
		event.setSessions(achievementJson.getString("sessions"));
		event.setSessionType(achievementJson.getString("sessionType"));
		event.setSpeakingOrganizingEventsType(achievementJson.getString("speakingOrganizingEventsType"));
		event.setTitleOfConference(achievementJson.getString("titleOfConference"));
		event.setTitleOfEvent(achievementJson.getString("titleOfEvent"));
		event.setTypeOfEvent(achievementJson.getString("typeOfEvent"));
		entityManager.getTransaction().commit();
		entityManager.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public String getAchievement(Achievement achievement) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App" ;
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME) ; 
		EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
		SpeakingOrganizingEvent event = entityManager.find(SpeakingOrganizingEvent.class, achievement.getAchievementId());
		try {
			return new ObjectMapper().writeValueAsString(event) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
