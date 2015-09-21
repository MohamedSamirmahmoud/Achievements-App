package com.ibm.achievements.crud;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.CertificationsAndProgram;
import com.ibm.achievements.model.CertificationsAndProgramsProduct;

public class CertificationAndProgramProudctCRUD implements
		CertificationAndProgramsTypesCRUDI {

	@Override
	public boolean addCertificationAndPrograms(
			CertificationsAndProgram certificationsAndProgram,
			JSONObject jsonObject) {
		CertificationsAndProgramsProduct certificationsAndProgramsProduct = new CertificationsAndProgramsProduct();
		certificationsAndProgramsProduct
				.setCertificationsandprogram(certificationsAndProgram);
		certificationsAndProgramsProduct
				.setAchievementId(certificationsAndProgram.getAchievementId());
		try {
			certificationsAndProgramsProduct.setBrand(jsonObject
					.getString("brand"));
			certificationsAndProgramsProduct.setBusUnit(jsonObject
					.getString("busUnit"));
			certificationsAndProgramsProduct.setCertificationExam(jsonObject
					.getString("certificatioExam"));
			certificationsAndProgramsProduct.setProductName(jsonObject
					.getString("proudctName"));
			final String PERSISTENCE_UNIT_NAME = "Achievements-App";
			final EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = entityManagerFactory
					.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(certificationsAndProgramsProduct);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCertificationAndPrograms(JSONObject jsonObject) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		try {
			CertificationsAndProgramsProduct certificationsAndProgramsProduct = entityManager
					.find(CertificationsAndProgramsProduct.class,
							jsonObject.getInt("achievementId"));
			entityManager.getTransaction().begin();
			certificationsAndProgramsProduct.setBrand(jsonObject
					.getString("brand"));
			certificationsAndProgramsProduct.setBusUnit(jsonObject
					.getString("busUnit"));
			certificationsAndProgramsProduct.setCertificationExam(jsonObject
					.getString("certificatioExam"));
			certificationsAndProgramsProduct.setProductName(jsonObject
					.getString("proudctName"));
			entityManager.getTransaction().commit();
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public String getCertificationsAndProgram(int achievementId) {
		final String PERSISTENCE_UNIT_NAME = "Achievements-App";
		final EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		CertificationsAndProgramsProduct certificationsAndProgramsProduct = entityManager
				.find(CertificationsAndProgramsProduct.class,
						achievementId);
		ObjectMapper mapper = new ObjectMapper() ; 
     try {
		return mapper.writeValueAsString(certificationsAndProgramsProduct) ;
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

}
