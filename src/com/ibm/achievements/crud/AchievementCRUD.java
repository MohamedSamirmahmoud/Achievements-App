package com.ibm.achievements.crud;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.FormParam;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.Achievement;
import com.ibm.achievements.model.Employee;

public abstract class AchievementCRUD {
	public static List<Achievement> getAchievementsByBrandAndEmployeeId(String brand , int employeeId){
		final String PERSISTENCE_UNIt_NAME = "Achievements-App" ;
		final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIt_NAME);
		EntityManager entityManager = factory.createEntityManager() ;
		Query query = entityManager.createQuery("SELECT a from Achievement a where a.employeeId=:employeeId") ;
		query.setParameter("employeeId", employeeId) ;
		List<Achievement>achievements = query.getResultList() ;
		return achievements ;
	}
	public static Achievement addBaseAchievement(String AcheievementJson) {
		Achievement achievement = new Achievement();
		try {
			JSONObject jsonObject = new JSONObject(AcheievementJson);
			achievement.setAchievementType(jsonObject.get("type").toString());
			achievement.setComment(jsonObject.get("comment").toString());
			achievement.setLobName(jsonObject.get("lobName").toString());
			achievement.setStatus("Pending");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = dateFormat.parse(jsonObject.get("date").toString());
			achievement.setAchievementDate(date);
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Achievements-App");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			Employee employee = (Employee)entityManager.find(Employee.class, Integer
							.valueOf(jsonObject.get("employeeId").toString())) ;
			achievement.getEmployees().add(employee);
			employee.getAchievements().add(achievement);
			entityManager.persist(achievement);
			entityManager.getTransaction().commit();
			entityManager.close();
			return achievement;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		return null;
	}

	public abstract boolean addAchievement(Achievement achievement,
			String achievementJson);

	public static boolean updateBaseAchievement(JSONObject AcheievementJson) {

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
			Date date = dateFormat.parse(AcheievementJson.get("date").toString());
			final String PERSISTENCE_UNIT_NAME = "Achievements-App";
			final EntityManagerFactory factory = Persistence
					.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin(); 
			Query query = em
					.createQuery("UPDATE Achievement a SET a.achievementType= :achievementType, a.comment= :comment, a.lobName= :lobName, a.status= :status , a.achievementDate = :achievementDate , a.brand=:brand WHERE a.achievementId= :achievementId ");
			query.setParameter("achievementId",
					Integer.valueOf(AcheievementJson.get("achievementId").toString()));
			query.setParameter("status", AcheievementJson.get("status").toString());
			query.setParameter("comment", AcheievementJson.get("comment").toString());
			query.setParameter("achievementDate", date);
			query.setParameter("achievementType", AcheievementJson.get("type")
					.toString());
			query.setParameter("lobName", AcheievementJson.get("lobName").toString());
			query.setParameter("brand", AcheievementJson.get("brand").toString()) ;
			query.executeUpdate();
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public abstract boolean updateAchievement(JSONObject achievementJson);

	public static Achievement getBaseAchievement(String achievementId) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager
				.createQuery("SELECT a FROM Achievement a where a.achievementId=:achievementId");
		query.setParameter("achievementId", Integer.valueOf(achievementId));
		return (Achievement) query.getSingleResult();
	}

	public abstract String getAchievement(Achievement achievement);

	public static boolean deleteAchievement(int achievementId) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager
				.createQuery("UPDATE Achievement e SET e.markedAsDeleted=1 where e.achievementId=:achievementId");
		query.setParameter("achievementId", achievementId);
		if (query.executeUpdate() > 0)
			return true;
		return false;
	}
	
	
	public static  List<Achievement> getAchievementbyMonth( String year , String month , String managerId){
		final String PERSISTANCE_UNIT_NAME = "Achievements-App" ;
		final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createQuery("SELECT A FROM Achievement A JOIN A.employees E WHERE E.manager.employeeId=:managerId  AND A.achievementDate LIKE '%"+year+"-"+month+"-%'");
	    query.setParameter("managerId", Integer.valueOf(managerId)) ;
	    List<Achievement> achievements = query.getResultList();
		return  achievements;
		
	}    
	
	
}
