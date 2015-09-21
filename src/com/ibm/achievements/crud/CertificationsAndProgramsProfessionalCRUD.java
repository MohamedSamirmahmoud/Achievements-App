package com.ibm.achievements.crud;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.CertificationsAndProgram;
import com.ibm.achievements.model.CertificationsAndProgramsProfessional;

public class CertificationsAndProgramsProfessionalCRUD implements
		CertificationAndProgramsTypesCRUDI {

	@Override
	public boolean addCertificationAndPrograms(
			CertificationsAndProgram certificationsAndProgram,
			JSONObject jsonObject) {
		CertificationsAndProgramsProfessional certificationsAndProgramsProfessional = new CertificationsAndProgramsProfessional();
		certificationsAndProgramsProfessional
				.setCertificationsandprogram(certificationsAndProgram);
		try {
			certificationsAndProgramsProfessional
					.setAchievementId(certificationsAndProgram
							.getAchievementId());
			certificationsAndProgramsProfessional
					.setProfessionalLevel(jsonObject.getString("level"));
			certificationsAndProgramsProfessional
					.setProfessionalType(jsonObject
							.getString("professionalType"));
			String PERSISENCE_UNIT_NAME = "Achievements_App";
			EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory(PERSISENCE_UNIT_NAME);
			EntityManager entityManager = entityManagerFactory
					.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(certificationsAndProgramsProfessional);
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
		String PERSISENCE_UNIT_NAME = "Achievements_App";
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(PERSISENCE_UNIT_NAME);
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		try {
			CertificationsAndProgramsProfessional certificationsAndProgramsProfessional = entityManager
					.find(CertificationsAndProgramsProfessional.class,
							jsonObject.getInt("achievementId"));
			entityManager.getTransaction().begin();
			certificationsAndProgramsProfessional
					.setProfessionalLevel(jsonObject.getString("level"));
			certificationsAndProgramsProfessional
					.setProfessionalType(jsonObject
							.getString("professionalType"));
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
	public String getCertificationsAndProgram(int achievementId) {
		String PERSISENCE_UNIT_NAME = "Achievements_App";
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(PERSISENCE_UNIT_NAME);
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		CertificationsAndProgramsProfessional certificationsAndProgramsProfessional = entityManager
				.find(CertificationsAndProgramsProfessional.class,
						achievementId);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper
					.writeValueAsString(certificationsAndProgramsProfessional);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
