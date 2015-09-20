package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CERTIFICATIONSANDPROGRAMS database table.
 * 
 */
@Entity
@Table(name="CERTIFICATIONSANDPROGRAMS")
@NamedQuery(name="CertificationsAndProgram.findAll", query="SELECT c FROM CertificationsAndProgram c")
public class CertificationsAndProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String typeOfCertification;

	//uni-directional one-to-one association to Achievement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID" , insertable=false , updatable=false)
	private Achievement achievement;

	public CertificationsAndProgram() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementid) {
		this.achievementId = achievementid;
	}

	public String getTypeOfCertification() {
		return this.typeOfCertification;
	}

	public void setTypeOfCertification(String typeOfCertification) {
		this.typeOfCertification = typeOfCertification;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

}