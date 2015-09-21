package com.ibm.achievements.crud;
import java.io.IOException;
import java.text.ParseException;



















import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.Achievement;
import com.ibm.achievements.model.BoardReview;
import com.ibm.achievements.model.Enablement;

public class EnablementCRUD extends AchievementCRUD{

	@Override
	public boolean addAchievement(Achievement achievement,
			String achievementJson) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonObject = new JSONObject(achievementJson);
			Enablement enablement = new Enablement() ;
			enablement.setAchievement(achievement);
			enablement.setAchievementId(achievement.getAchievementId());
		    enablement.setBusUnit(jsonObject.get("busUnit").toString());
		    enablement.setDuration(jsonObject.get("duration").toString());
		    enablement.setEvent(jsonObject.get("event").toString());
		    enablement.setNumberOfAttendants(Integer.valueOf(jsonObject.get("numberOfAttendants").toString()));
		    enablement.setTitle(jsonObject.getString("title").toString());
		    enablement.setTypeOfEnablement(jsonObject.get("typeOfEnablement").toString());
			final String PERSISTENT_UNIT_NAME= "Achievements-App" ; 
			final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME) ;
			EntityManager entityManager = factory.createEntityManager() ; 
			entityManager.getTransaction().begin(); 
			entityManager.persist(enablement);
			entityManager.getTransaction().commit();
			entityManager.close();
			if(jsonObject.get("typeOfEnablement").toString()=="Customer"){
			Class classDefenation;
			try {
				classDefenation = Class.forName("com.ibm.achievements.crud."+"Enablement" +enablement.getTypeOfEnablement()+ "CRUD");
				EnablementTypesCRUDI enablementTypesCRUDI = (EnablementTypesCRUDI) classDefenation.newInstance();
				enablementTypesCRUDI.addEnablement(enablement, jsonObject) ;
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			return true ;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAchievement(JSONObject achievementJson) {
		// TODO Auto-generated method stub
		try{
				
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
 		EntityManager entityManger = factory.createEntityManager();
 		entityManger.getTransaction().begin();
 		Query query =  entityManger.createQuery("UPDATE Enablement e SET e.busUnit= :busUnit, e.duration= :duration, e.event=:event ,e.numberOfAttendants=:numberOfAttendants, e.title=:title, e.typeOfEnablement=:typeOfEnablement where e.achievementId= :achievementId");
 	    query.setParameter("busUnit",achievementJson.get("busUnit").toString());
 	    query.setParameter("duration", achievementJson.get("duration").toString());
 	    query.setParameter("event", achievementJson.get("event").toString());
 	    query.setParameter("numberOfAttendants", Integer.valueOf(achievementJson.get("numberOfAttendants").toString()));
 	    query.setParameter("title", achievementJson.get("title").toString());
 	    query.setParameter("typeOfEnablement", achievementJson.get("typeOfEnablement").toString());
 	    query.setParameter("achievementId", Integer.valueOf(achievementJson.get("achievementId").toString()));
 		query.executeUpdate();
		entityManger.getTransaction().commit();
		entityManger.close();
		if(achievementJson.get("typeOfEnablement").toString().equals("Customer")){
		Class classDefenation;
		try {
			classDefenation = Class.forName("com.ibm.achievements.crud."+"Enablement" + achievementJson.get("TypeOfEnablement").toString() + "CRUD");
			EnablementTypesCRUDI enablementTypesCRUDI;
			enablementTypesCRUDI = (EnablementTypesCRUDI) classDefenation.newInstance();
			enablementTypesCRUDI.updateEnablement(achievementJson ) ; 
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
 	   return true ;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false ;
	}

	@Override
	public String getAchievement(Achievement achievement) {
		// TODO Auto-generated method stub
		final String PERSISTENCE_UNIT_NAME = "Achievements-App" ;
	    final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    EntityManager entityManager = factory.createEntityManager() ;
	    Query query= entityManager.createQuery("SELECT e FROM Enablement e WHERE e.achievementId=:achievementId");
	    query.setParameter("achievementId", achievement.getAchievementId());
	    Enablement enablement= (Enablement)query.getSingleResult();
	    enablement.setAchievement(achievement);
	    if(enablement.getTypeOfEnablement().equals("Customer")){
	    Class classDefenation;
		try {
			classDefenation = Class.forName("com.ibm.achievements.crud."+"Enablement" + enablement.getTypeOfEnablement() + "CRUD");
			MentorshipTypesCRUDI mentorshipTypesCRUDI;
            mentorshipTypesCRUDI = (MentorshipTypesCRUDI) classDefenation.newInstance();
			
			return mentorshipTypesCRUDI.getAchievement(enablement.getAchievementId()+"") ;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
		return null;
	}

}
