package com.ibm.achievements.crud;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.Mentorship;
import com.ibm.achievements.model.MentorshipCertification;

public class MentorshipCertificationCRUD implements MentorshipTypesCRUDI{

	@Override
	public boolean addMentorship(Mentorship mentorship, JSONObject mentorshipJson) {
		MentorshipCertification mentorshipCertification= new MentorshipCertification();
		mentorshipCertification.setMentorship(mentorship);
		mentorshipCertification.setAchievementId(mentorship.getAchievementId());
		try {
			mentorshipCertification.setTypeOfCertification(mentorshipJson.getString("typeOfCertification"));
			final String PERSISTENCE_UNIT_NAME = "Achievements-App"  ;
			final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
			entityManager.getTransaction().begin();
	        entityManager.persist(mentorshipCertification);
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
	public boolean updateMentorship(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		final String PERSISTANCE_UNIT_NAME="Achievement-app";
		final EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager entityManager= entityManagerFactory.createEntityManager();
		try {
			MentorshipCertification mentorshipCertification=entityManager.find(MentorshipCertification.class, jsonObject.getInt("achievementId"));
			entityManager.getTransaction().begin();
			mentorshipCertification.setTypeOfCertification(jsonObject.getString("typeOfCertification"));
			entityManager.getTransaction().commit();
			entityManager.close();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

                
		return false;
	}

	@Override
	public String getAchievement(String achievementId) {
       final String PERSISTANCE_UNIT_NAME ="Achievement-app";
       final EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
       EntityManager entityManager = entityManagerFactory. createEntityManager();
       MentorshipCertification mentorshipCertification = entityManager . find(MentorshipCertification.class, Integer.valueOf("achievementId"));
       ObjectMapper mapper = new ObjectMapper() ; 
		try {
			return mapper.writeValueAsString(mentorshipCertification);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return null ; 	
	}

}
