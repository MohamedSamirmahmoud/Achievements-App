package com.ibm.achievements.crud;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.Mentorship;
import com.ibm.achievements.model.MentorshipSkill;
import com.ibm.db2.jcc.am.me;

public class MentorshipSkillCRUD implements MentorshipTypesCRUDI{

	@Override
	public boolean addMentorship(Mentorship mentorship, JSONObject mentorshipJson) {
    MentorshipSkill mentorshipSkill=new MentorshipSkill();
    mentorshipSkill.setMentorship(mentorship);
    mentorshipSkill.setAchievementId(mentorship.getAchievementId());
    try {
		mentorshipSkill.setArea(mentorshipJson.getString("area"));
		mentorshipSkill.setSkillDuration(mentorshipJson.getInt("duration"));
		final String PERSISTENCE_UNIT_NAME = "Achievements-App"  ;
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
		entityManager.getTransaction().begin();
        entityManager.persist(mentorshipSkill);
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
     final String PERSISTENCE_UNIT_NAME = "Achievements-App" ; 
     final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	 EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
	 
	 try {
		MentorshipSkill mentorshipSkill = entityManager.find(MentorshipSkill.class, jsonObject.getInt("achievementId")) ;
		entityManager.getTransaction().begin();
		mentorshipSkill.setArea(jsonObject.getString("area"));
		mentorshipSkill.setSkillDuration(jsonObject.getInt("duration"));
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
	public String getAchievement(String achievementId) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App" ; 
	     final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		 EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
		 
		 MentorshipSkill mentorshipSkill =  entityManager.find(MentorshipSkill.class,Integer.valueOf(achievementId)) ; 
		 ObjectMapper mapper = new ObjectMapper() ; 
		try {
			return mapper.writeValueAsString(mentorshipSkill);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return null ; 	
	}

}
