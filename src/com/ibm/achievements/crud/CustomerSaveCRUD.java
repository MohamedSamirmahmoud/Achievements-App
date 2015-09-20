package com.ibm.achievements.crud;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.Achievement;
import com.ibm.achievements.model.CustomerSave;
import com.ibm.achievements.model.Disclosure;

public class CustomerSaveCRUD extends AchievementCRUD{

	@Override
	public boolean addAchievement(Achievement achievement,
			String achievementJson) {
		// TODO Auto-generated method stub
		
		try {
			JSONObject jsonObject = new JSONObject(achievementJson);
			CustomerSave customerSave = new CustomerSave();
			customerSave.setAchievement(achievement);
			customerSave.setAchievementId(achievement.getAchievementId());
			customerSave.setBrand(jsonObject.get("brand").toString());
			customerSave.setBusinessUnits(jsonObject.get("businessUnits").toString());
			customerSave.setCountry(jsonObject.get("country").toString());
			customerSave.setCustomerName(jsonObject.get("customerName").toString());
			customerSave.setCustomerProblem(jsonObject.get("CustomerProblem").toString());
			customerSave.setEmployeeContribution(jsonObject.get("employeeContribution").toString());
			customerSave.setEngagementName(jsonObject.get("engagementName").toString());
			final String PERSISTENT_UNIT_NAME = "Achievements-App";
			final EntityManagerFactory factory = Persistence
					.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(customerSave);
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
		// TODO Auto-generated method stub
		
		final String PERSISTANT_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factroy = Persistence
				.createEntityManagerFactory(PERSISTANT_UNIT_NAME);
		EntityManager entityManager = factroy.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager
				.createQuery("UPDATE CustomerSave C SET C.brand =:brand, C.businessUnits=:businessUnits , C.country=:country ,C.customerName=:customerName , C.CustomerProblem=:CustomerProblem  ,C.employeeContribution=:employeeContribution ,C.engagementName=:engagementName  WHERE D.achievementId=:achievementId");
		try {
			query.setParameter("brand", achievementJson.get("brand").toString());
			query.setParameter("businessUnits", achievementJson.get("businessUnits").toString());
			query.setParameter("country", achievementJson.get("country").toString());
			query.setParameter("customerName", achievementJson.get("customerName").toString());
			query.setParameter("CustomerProblem", achievementJson.get("CustomerProblem").toString());
			query.setParameter("employeeContribution", achievementJson.get("employeeContribution").toString());
			query.setParameter("engagementName", achievementJson.get("engagementName").toString());
			query.setParameter("achievementId",Integer.valueOf(achievementJson.get("achievementId").toString()));
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
			Query query = entityManager.createQuery("SELECT c FROM CustomerSave c WHERE c.achievementId=:achievementId");
			query.setParameter("achievementId", achievement.getAchievementId());
			CustomerSave customerSave = (CustomerSave) query.getSingleResult();
			customerSave.setAchievement(achievement);
			ObjectMapper mapper = new ObjectMapper();
			try {
				
				return mapper
						.writeValueAsString(customerSave);
			} catch (  IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

}
