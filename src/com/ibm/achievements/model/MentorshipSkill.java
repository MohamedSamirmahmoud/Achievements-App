package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MENTORSHIPSKILL database table.
 * 
 */
@Entity
@NamedQuery(name="MentorshipSkill.findAll", query="SELECT m FROM MentorshipSkill m")
public class MentorshipSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String area;

	private String brand;

	private int skillDuration;

	//uni-directional one-to-one association to Mentorship
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID", insertable=false , updatable=false)
	private Mentorship mentorship;

	public MentorshipSkill() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}


	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getSkillDuration() {
		return this.skillDuration;
	}

	public void setSkillDuration(int skillDuration) {
		this.skillDuration = skillDuration;
	}

	public Mentorship getMentorship() {
		return this.mentorship;
	}

	public void setMentorship(Mentorship mentorship) {
		this.mentorship = mentorship;
	}

}