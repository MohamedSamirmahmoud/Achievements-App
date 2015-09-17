package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SPEAKINGORGANIZINGEVENTS database table.
 * 
 */
@Entity
@Table(name="SPEAKINGORGANIZINGEVENTS")
@NamedQuery(name="SpeakingOrganizingEvent.findAll", query="SELECT s FROM SpeakingOrganizingEvent s")
public class SpeakingOrganizingEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String businessUnits;

	private String country;

	private String sessions;

	private String sessionType;

	private String speakingOrganizingEventsType;

	private String titleOfConference;

	private String titleOfEvent;

	private String typeOfEvent;

	//uni-directional one-to-one association to Achievement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID")
	private Achievement achievement;

	public SpeakingOrganizingEvent() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}

	
	public String getBusinessUnits() {
		return this.businessUnits;
	}

	public void setBusinessUnits(String businessUnits) {
		this.businessUnits = businessUnits;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSessions() {
		return this.sessions;
	}

	public void setSessions(String sessions) {
		this.sessions = sessions;
	}

	public String getSessionType() {
		return this.sessionType;
	}

	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

	public String getSpeakingOrganizingEventsType() {
		return this.speakingOrganizingEventsType;
	}

	public void setSpeakingOrganizingEventsType(String speakingOrganizingEventsType) {
		this.speakingOrganizingEventsType = speakingOrganizingEventsType;
	}

	public String getTitleOfConference() {
		return this.titleOfConference;
	}

	public void setTitleOfConference(String titleOfConference) {
		this.titleOfConference = titleOfConference;
	}

	public String getTitleOfEvent() {
		return this.titleOfEvent;
	}

	public void setTitleOfEvent(String titleOfEvent) {
		this.titleOfEvent = titleOfEvent;
	}

	public String getTypeOfEvent() {
		return this.typeOfEvent;
	}

	public void setTypeOfEvent(String typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

}