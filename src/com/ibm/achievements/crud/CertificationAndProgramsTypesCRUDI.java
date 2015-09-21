package com.ibm.achievements.crud;

import org.json.JSONObject;

import com.ibm.achievements.model.CertificationsAndProgram;

public interface CertificationAndProgramsTypesCRUDI {
	public boolean addCertificationAndPrograms(CertificationsAndProgram certificationsAndProgram , JSONObject jsonObject) ;
	public boolean updateCertificationAndPrograms(JSONObject jsonObject) ; 
	public String certificationsAndProgram(int achievementId) ;
 
}
