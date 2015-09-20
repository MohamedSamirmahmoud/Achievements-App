package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CUSTOMERREFERENCES database table.
 * 
 */
@Entity
@Table(name="CUSTOMERREFERENCES")
@NamedQuery(name="CustomerReference.findAll", query="SELECT c FROM CustomerReference c")
public class CustomerReference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String brand;

	private String busUnit;

	private String country;

	private String customerName;

	private String engagementName;

	//uni-directional one-to-one association to Achievement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID", insertable=false , updatable=false)
	private Achievement achievement;

	public CustomerReference() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}


	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

}