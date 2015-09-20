package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SUCCESSSTORIES database table.
 * 
 */
@Entity
@Table(name="SUCCESSSTORIES")
@NamedQuery(name="SuccessStory.findAll", query="SELECT s FROM SuccessStory s")
public class SuccessStory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String busUnit;

	private String country;

	private String customerName;

	private String engagementName;

	private String successStoryLink;

	@Column(name="\"TYPE\"")
	private String type;

	//uni-directional one-to-one association to Achievement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID", insertable=false , updatable=false)
	private Achievement achievement;

	public SuccessStory() {
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

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEngagementName() {
		return this.engagementName;
	}

	public void setEngagementName(String engagementName) {
		this.engagementName = engagementName;
	}

	public String getSuccessStoryLink() {
		return this.successStoryLink;
	}

	public void setSuccessStoryLink(String successStoryLink) {
		this.successStoryLink = successStoryLink;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

}