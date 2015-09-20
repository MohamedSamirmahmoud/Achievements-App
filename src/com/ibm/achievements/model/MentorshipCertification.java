package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MENTORSHIPCERTIFICATION database table.
 * 
 */
@Entity
@NamedQuery(name="MentorshipCertification.findAll", query="SELECT m FROM MentorshipCertification m")
public class MentorshipCertification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String typeOfCertification;

	//uni-directional one-to-one association to Mentorship
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID", insertable=false , updatable=false)
	private Mentorship mentorship;

	public MentorshipCertification() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}


	public String getTypeOfCertification() {
		return this.typeOfCertification;
	}

	public void setTypeOfCertification(String typeOfCertification) {
		this.typeOfCertification = typeOfCertification;
	}

	public Mentorship getMentorship() {
		return this.mentorship;
	}

	public void setMentorship(Mentorship mentorship) {
		this.mentorship = mentorship;
	}

}