package com.ibm.achievements.crud;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.Achievement;
import com.ibm.achievements.model.RevenueInfelunce;

public class RevenueInfluenceCRUD extends AchievementCRUD{

	@Override
	public boolean addAchievement(Achievement achievement,
			String achievementJson) {
		try {
			JSONObject jsonObject = new JSONObject(achievementJson);
			RevenueInfelunce infelunce = new RevenueInfelunce() ;
			 infelunce.setAchievement(achievement);
			 infelunce.setAmount(jsonObject.getInt("amount"));
			 infelunce.setBrand(jsonObject.get("brand").toString());
			 infelunce.setBusinessUnits(jsonObject.getString("businessUnits"));
			 infelunce.setAchievementId(achievement.getAchievementId());
			 infelunce.setContributionType(jsonObject.getString("contributionType"));
			 infelunce.setCountry(jsonObject.getString("country"));
             infelunce.setCustomerName(jsonObject.getString("customerName"));
             infelunce.setDescriptionOfContribution(jsonObject.getString("descriptionOfContribution"));
             infelunce.setEngagementName(jsonObject.getString("engagementName"));
             infelunce.setRevenueInfelunceType(jsonObject.getString("revenueInfelunceType"));
             final String PERSISTENCE_UNIT_NAME = "Achievements-App" ; 
             final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
             EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
             entityManager.getTransaction().begin(); 
             entityManager.persist(infelunce);
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
		EntityManager  entityManager = entityManagerFactory.createEntityManager() ; 
		entityManager.getTransaction().begin();
		try {
			RevenueInfelunce infelunce =  entityManager.find(RevenueInfelunce.class, achievementJson.getInt("achievementId"));
			 infelunce.setAmount(achievementJson.getInt("amount"));
			 infelunce.setBrand(achievementJson.get("brand").toString());
			 infelunce.setBusinessUnits(achievementJson.getString("businessUnits"));
			 infelunce.setContributionType(achievementJson.getString("contributionType"));
			 infelunce.setCountry(achievementJson.getString("country"));
            infelunce.setCustomerName(achievementJson.getString("customerName"));
            infelunce.setDescriptionOfContribution(achievementJson.getString("descriptionOfContribution"));
            infelunce.setEngagementName(achievementJson.getString("engagementName"));
            infelunce.setRevenueInfelunceType(achievementJson.getString("revenueInfelunceType"));
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
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
    RevenueInfelunce infelunce = entityManager.find(RevenueInfelunce.class, achievement.getAchievementId()) ;
	ObjectMapper mapper = new ObjectMapper() ; 	
		try {
			return mapper.writeValueAsString(infelunce);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ; 
	}

}
