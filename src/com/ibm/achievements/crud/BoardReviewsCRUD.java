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
import com.ibm.achievements.model.BoardReview;

public class BoardReviewsCRUD extends AchievementCRUD {

	@Override
	public boolean addAchievement(Achievement achievement,
			String achievementJson) {

		try {
			JSONObject jsonObject = new JSONObject(achievementJson);
			BoardReview boardReview = new BoardReview();
			boardReview.setAchievement(achievement);
			boardReview.setAchievementId(achievement.getAchievementId());
			boardReview.setFlag(Integer.valueOf(jsonObject.get("flag").toString()));
			boardReview.setTypeOfCertificate(jsonObject.get("typeOfCertificate").toString());
			boardReview.setReviewType(jsonObject.get("reviewType").toString());
			boardReview.setBoardReviewLevel(jsonObject.get("boardReviewLevel").toString());
			final String PERSISTENT_UNIT_NAME = "Achievements-App";
			final EntityManagerFactory factory = Persistence
					.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(boardReview);
			entityManager.getTransaction().commit();
			entityManager.close();
			
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
			final String PERSISTENT_UNIT_NAME = "Achievements-App";
			final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			Query query =  entityManager.createQuery("UPDATE BroadReview Br SET Br.flag=:flag, Br.typeOfCertificate=:typeOfCertificate ,Br.reviewType=:reviewType ,Br.boardReviewLevel=:boardReviewLevel  where Br.achievementId= :achievementId");
			BoardReview boardReview = new BoardReview();
			boardReview.setFlag(Integer.valueOf(achievementJson.get("flag").toString()));
			boardReview.setTypeOfCertificate(achievementJson.get("typeOfCertificate").toString());
			boardReview.setReviewType(achievementJson.get("reviewType").toString());
			boardReview.setBoardReviewLevel(achievementJson.get("boardReviewLevel").toString());
			query.setParameter("achievementId", Integer.valueOf(achievementJson.get("achievementId").toString()));
		 	query.executeUpdate();
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
     final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
     EntityManager entityManager = factory.createEntityManager();
     Query query = entityManager.createQuery("SELECT br from BoardReview br WHERE br.achievementid=:achievementid ") ;
     query.setParameter("achievementid", achievement.getAchievementId()) ;
     BoardReview boardReview = (BoardReview)query.getSingleResult() ;
     boardReview.setAchievement(achievement);
     ObjectMapper mapper = new ObjectMapper() ;
     try {
	  return  mapper.writeValueAsString(boardReview) ;
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
