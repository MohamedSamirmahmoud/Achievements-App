package com.ibm.achievements.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.PUBLIC_MEMBER;

import sun.security.jgss.GSSCaller;

import com.ibm.achievements.crud.AchievementCRUD;
import com.ibm.achievements.model.Achievement;
import com.ibm.achievements.model.BoardReview;
import com.ibm.achievements.model.Employee;

@Path("/AchievementQuerying")
public class AchievementQuerying {
	@GET
	@Path("/getAchievementsByEmployeeId/{employeeId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAchievementsByEmployeeId(
			@PathParam("employeeId") String employeeId) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query query = em
				.createQuery("SELECT e.achievements FROM Employee e where e.employeeId=:employeeId");
		query.setParameter("employeeId", Integer.valueOf(employeeId));

		List<Achievement> achievements = query.getResultList();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(df);
		try {
			
			return mapper.writeValueAsString(achievements) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@GET
	@Path("/getAchievementsByStatus/{status}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAchievementsByStatus(@PathParam("status") String status) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query query = em
				.createQuery("SELECT a FROM Achievement a WHERE a.status = :status ");
		query.setParameter("status", status);

		List<Achievement> achievements = query.getResultList();
		JSONObject json = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		try {
			json.put("achievements", mapper.writeValueAsString(achievements));
			return json.toString();

		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/getAchievementsByEmployeeIdAndStatus/{employeeId}/{status}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAchievementsByEmployeeIdAndStatus(
			@PathParam("employeeId") String employeeId,
			@PathParam("status") String status) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query query = em
				.createQuery("SELECT a FROM Achievement a JOIN a.employees e WHERE e.employeeId= :employeeId AND a.status = :status");
		query.setParameter("employeeId", Integer.valueOf(employeeId));
		query.setParameter("status", status);

		List<Achievement> achievements = query.getResultList();
		JSONObject json = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		try {
			json.put("achievements", mapper.writeValueAsString(achievements));
			return json.toString();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonGenerationException e) {
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

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getAchievmentByType/{type}/{managerId}")
	public String getAchievmentsByType(@PathParam("type") String type,
			@PathParam("managerId") String managerId) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query query = em
				.createQuery("SELECT a FROM Achievement a JOIN a.employees e WHERE a.achievementType= :type AND e.manager.employeeId= :managerId ");
		query.setParameter("type", type);
		query.setParameter("managerId", Integer.valueOf(managerId));
		List<Achievement> achievment = query.getResultList();
		JSONObject Jsonobj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Jsonobj.put("achievements", mapper.writeValueAsString(achievment));
			return Jsonobj.toString();
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getAchievementsByBrandAndEmployeeId")
	public String getAchievementsByBrand(@FormParam("brand") String brand,
			@FormParam("employeeId") String employeeId) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager
				.createQuery("SELECT e.achievements FROM Employee e JOIN e.achievements a where e.employeeId=:employeeId and a.brand=:brand");
		query.setParameter("employeeId", Integer.valueOf(employeeId));
		query.setParameter("brand", brand);
		List<Achievement> achievements = query.getResultList();
		JSONObject json = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		try {
			json.put("achievements", mapper.writeValueAsString(achievements));

		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/GetAchievementById/{achievementId}")
	public String getAchievementById(
			@PathParam("achievementId") String achievementId) {
		Achievement achievement = AchievementCRUD
				.getBaseAchievement(achievementId);
		try {
			Class classDefination = Class.forName("com.ibm.achievements.dao."
					+ achievement.getAchievementType() + "CRUD");
			AchievementCRUD achievementCRUD = (AchievementCRUD) classDefination
					.newInstance();
			return achievementCRUD.getAchievement(achievement);
		} catch (Exception exception) {
		}
		return null;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getAchievementsByBrandAndManagerId")
	public String getAchievemntsByBrandAndManagerId(
			@FormParam("brand") String brand,
			@FormParam("managerId") String managerId) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager
				.createQuery("SELECT a FROM Achievement a JOIN a.employees e where a.brand=:brand and e.manager.employeeId=:managerId");
		query.setParameter("managerId", Integer.valueOf(managerId));
		query.setParameter("brand", brand);
		List<Employee> employees = query.getResultList();
		JSONObject json = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		try {
			json.put("achievements", mapper.writeValueAsString(employees));

		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getAchievementByMonth")
	public String getAchievementbyMonth(@FormParam("year") String year,
			@FormParam("month") String month,
			@FormParam("managerId") String managerId) {

		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonObject.put("achievements", mapper
					.writeValueAsString(AchievementCRUD.getAchievementbyMonth(
							year, month, managerId)));
			return jsonObject.toString();
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getAchievementByQuarter")
	public String getAchievementByQuarter(@FormParam("year") String year,
			@FormParam("quarter") String quarter,
			@FormParam("managerId") String managerId) {
		List<Achievement> retVal = new ArrayList<Achievement>();

		final String PERSISTANCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		System.out.println("HEllo");
		EntityManager em = factory.createEntityManager();
		if (quarter.equals("1")) {
			System.out.println("Hello");
			retVal.addAll(AchievementCRUD.getAchievementbyMonth(year , "01",
					managerId));
			retVal.addAll(AchievementCRUD.getAchievementbyMonth(year,"02", 
					managerId));
			retVal.addAll(AchievementCRUD.getAchievementbyMonth(year,"03", 
					managerId));

		} else if (quarter.equals("2")) {
			retVal.addAll(AchievementCRUD.getAchievementbyMonth(year,"04",
					managerId));
			retVal.addAll(AchievementCRUD.getAchievementbyMonth(year,"05",
					managerId));
			retVal.addAll(AchievementCRUD.getAchievementbyMonth(year,"06", 
					managerId));

		} else if (quarter.equals("3")) {
		    retVal.addAll(AchievementCRUD.getAchievementbyMonth(year,"07",
					managerId));
			retVal.addAll(AchievementCRUD.getAchievementbyMonth(year,"08", 
					managerId));
			retVal.addAll(AchievementCRUD.getAchievementbyMonth(year,"09",
					managerId));

		} else if (quarter.equals("4")) {
			retVal.addAll(AchievementCRUD.getAchievementbyMonth(year,"10",
					managerId));
			retVal.addAll(AchievementCRUD.getAchievementbyMonth(year,"11",
					managerId));
			retVal.addAll(AchievementCRUD.getAchievementbyMonth(year,"12",
					managerId));

		}
		JSONObject jsonObject = new JSONObject() ;
		ObjectMapper mapper = new ObjectMapper () ; 
		try {
			jsonObject.put("achievements", mapper.writeValueAsString(retVal)) ;
		return jsonObject.toString() ;
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
