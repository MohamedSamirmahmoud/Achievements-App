package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CERTIFICATIONSANDPROGRAMSPROFESSIONAL database table.
 * 
 */
@Entity
@NamedQuery(name="CertificationsAndProgramsProfessional.findAll", query="SELECT c FROM CertificationsAndProgramsProfessional c")
public class CertificationsAndProgramsProfessional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String professionalLevel;

	private String professionalType;

	//uni-directional one-to-one association to CertificationsAndProgram
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID" , insertable=false , updatable=false)
	private CertificationsAndProgram certificationsandprogram;

	public CertificationsAndProgramsProfessional() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}


	public String getProfessionalLevel() {
		return this.professionalLevel;
	}

	public void setProfessionalLevel(String professionalLevel) {
		this.professionalLevel = professionalLevel;
	}

	public String getProfessionalType() {
		return this.professionalType;
	}

	public void setProfessionalType(String professionalType) {
		this.professionalType = professionalType;
	}

	public CertificationsAndProgram getCertificationsandprogram() {
		return this.certificationsandprogram;
	}

	public void setCertificationsandprogram(CertificationsAndProgram certificationsandprogram) {
		this.certificationsandprogram = certificationsandprogram;
	}

}