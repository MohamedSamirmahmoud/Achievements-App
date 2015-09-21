package com.ibm.achievements.crud;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.crud. EnablementTypesCRUDI;
import com.ibm.achievements.model.Enablement;
import com.ibm.achievements.model.EnablementCustomer;
import com.ibm.achievements.model.MentorshipSkill;



public class EnablementCustomerCRUD implements EnablementTypesCRUDI {

	@Override
	public boolean addEnablement(Enablement enablement,JSONObject enablementJson) {
		EnablementCustomer enablementCustomer=new EnablementCustomer();
		enablementCustomer.setEnablement(enablement);
		enablementCustomer.setAchievementId(enablement.getAchievementId());
	    try {
	    	enablementCustomer.setCustomerName(enablementJson.getString("customerName"));
	    	enablementCustomer.setCustomerType(enablementJson.getString("customerType"));
			final String PERSISTENCE_UNIT_NAME = "Achievements-App"  ;
			final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
			entityManager.getTransaction().begin();
	        entityManager.persist(enablementCustomer);
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
	public boolean updateEnablement(JSONObject jsonObject) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App" ; 
	     final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		 EntityManager entityManager = entityManagerFactory.createEntityManager() ; 
		 
		 try {
			 EnablementCustomer enablementCustomer = entityManager.find(EnablementCustomer.class, jsonObject.getInt("achievementId")) ;
			entityManager.getTransaction().begin();
			enablementCustomer.setCustomerName(jsonObject.getString("customerName"));
			enablementCustomer.setCustomerType(jsonObject.getString("customerType"));
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
		 
		 EnablementCustomer enablementCustomer =  entityManager.find(EnablementCustomer.class,Integer.valueOf(achievementId)) ; 
		 ObjectMapper mapper = new ObjectMapper() ; 
		try {
			return mapper.writeValueAsString(enablementCustomer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return null ; 	
	}

	
	
	
	
	
}