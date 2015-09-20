package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MENTORSHIP database table.
 * 
 */
@Entity
@NamedQuery(name="Mentorship.findAll", query="SELECT m FROM Mentorship m")
public class Mentorship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String description;

	private String typeOfMentorship;

	//uni-directional one-to-one association to Achievement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID", insertable=false , updatable=false)
	private Achievement achievement;

	public Mentorship() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeOfMentorship() {
		return this.typeOfMentorship;
	}

	public void setTypeOfMentorship(String typeOfMentorship) {
		this.typeOfMentorship = typeOfMentorship;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

}