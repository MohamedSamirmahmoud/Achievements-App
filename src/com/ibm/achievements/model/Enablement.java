package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENABLEMENT database table.
 * 
 */
@Entity
@NamedQuery(name="Enablement.findAll", query="SELECT e FROM Enablement e")
public class Enablement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String busUnit;

	private String duration;

	@Column(name="\"EVENT\"")
	private String event;

	private int numberOfAttendants;

	private String title;

	private String typeOfEnablement;

	//uni-directional one-to-one association to Achievement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID", insertable=false , updatable=false)
	private Achievement achievement;

	public Enablement() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}


	public String getBusUnit() {
		return this.busUnit;
	}

	public void setBusUnit(String busUnit) {
		this.busUnit = busUnit;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public int getNumberOfAttendants() {
		return this.numberOfAttendants;
	}

	public void setNumberOfAttendants(int numberOfAttendants) {
		this.numberOfAttendants = numberOfAttendants;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTypeOfEnablement() {
		return this.typeOfEnablement;
	}

	public void setTypeOfEnablement(String typeOfEnablement) {
		this.typeOfEnablement = typeOfEnablement;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

}