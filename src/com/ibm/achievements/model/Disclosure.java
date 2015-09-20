package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DISCLOSURE database table.
 * 
 */
@Entity
@NamedQuery(name="Disclosure.findAll", query="SELECT d FROM Disclosure d")
public class Disclosure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	@Column(name="\"NUMBER\"")
	private int number;

	private String title;

	//uni-directional one-to-one association to Achievement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID", insertable=false , updatable=false)
	private Achievement achievement;

	public Disclosure() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}


	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

}