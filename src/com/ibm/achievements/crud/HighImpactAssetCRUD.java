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
import com.ibm.achievements.model.HighImpactAsset;

public class HighImpactAssetCRUD extends AchievementCRUD {

	@Override
	public boolean addAchievement(Achievement achievement,
			String achievementJson) {
		
		 try {
				JSONObject jsonObject = new JSONObject(achievementJson);
				HighImpactAsset highImpactAsset= new HighImpactAsset();
				highImpactAsset.setAchievement(achievement);
				highImpactAsset.setAchievementId(achievement.getAchievementId());
				highImpactAsset.setTypeOfAsset(jsonObject.get("typePfAsset").toString());
				highImpactAsset.setBrand(jsonObject.getString("brand"));
				highImpactAsset.setDescription(jsonObject.getString("description"));
				highImpactAsset.setHighImpactAssetName(jsonObject.getString("highImpactAssetName"));
				highImpactAsset.setTypeOfAsset2(jsonObject.getString("typeOfAsset2"));
				final String PERSISTENT_UNIT_NAME= "Achievements-App" ; 
				final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME) ;
				EntityManager entityManager = factory.createEntityManager() ; 
				entityManager.getTransaction().begin(); 
				entityManager.persist(highImpactAsset);
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
		// TODO Auto-generated method stub
		
		try{
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
 		EntityManager em = factory.createEntityManager();
 		em.getTransaction().begin();
 		Query query= em.createQuery("UPDATE HighImpactAsset h SET h.typeOfAsset=:typeOfAsset , h.brand=:brand , h.description=:description , h.highImpactAssetName=:highImpactAssetName , h.typeOfAsset2=:typeOfAsset2 where h.achievementId= :achievementId");
 		query.setParameter("typeOfAsset", achievementJson.get("typeOfAsset").toString());
 		query.setParameter("brand", Integer.valueOf(achievementJson.get("brand").toString()));
 		query.setParameter("description", Integer.valueOf(achievementJson.get("description").toString()));
 		query.setParameter("highImpactAssetName", Integer.valueOf(achievementJson.get("highImpactAssetName").toString()));
 		query.setParameter("typeOfAsset2", Integer.valueOf(achievementJson.get("typeOfAsset2").toString()));
 		query.setParameter("achievementId", Integer.valueOf(achievementJson.get("achievementId").toString()));
 		query.executeUpdate();
		em.getTransaction().commit();
	    em.close();
 		return true;
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	
	@Override
	public String getAchievement(Achievement achievement) {
		// TODO Auto-generated method stub
		final String PERSISTENCE_UNIT_NAME = "Achievements-App" ; 
	     final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	     EntityManager entityManager = factory.createEntityManager();
	     Query query= entityManager.createQuery("SELECT h FROM HighImpactAsset h WHERE h.achievementId=:achievementId");
	     query.setParameter("achievementid", achievement.getAchievementId()) ;
	     HighImpactAsset highImpactAsset= (HighImpactAsset)query.getSingleResult();
	    highImpactAsset.setAchievement(achievement);
	     ObjectMapper mapper = new ObjectMapper() ;
	     try {
		  return mapper.writeValueAsString(highImpactAsset);
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
