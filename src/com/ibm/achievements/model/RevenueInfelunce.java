package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the REVENUEINFELUNCE database table.
 * 
 */
@Entity
@NamedQuery(name="RevenueInfelunce.findAll", query="SELECT r FROM RevenueInfelunce r")
public class RevenueInfelunce implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private int amount;

	private String brand;

	private String businessUnits;

	private String contributionType;

	private String country;

	private String customerName;

	private String descriptionOfContribution;

	private String engagementName;

	private String revenueInfelunceType;

	//uni-directional one-to-one association to Achievement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID")
	private Achievement achievement;

	public RevenueInfelunce() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}


	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBusinessUnits() {
		return this.businessUnits;
	}

	public void setBusinessUnits(String businessUnits) {
		this.businessUnits = businessUnits;
	}

	public String getContributionType() {
		return this.contributionType;
	}

	public void setContributionType(String contributionType) {
		this.contributionType = contributionType;
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

	public String getDescriptionOfContribution() {
		return this.descriptionOfContribution;
	}

	public void setDescriptionOfContribution(String descriptionOfContribution) {
		this.descriptionOfContribution = descriptionOfContribution;
	}

	public String getEngagementName() {
		return this.engagementName;
	}

	public void setEngagementName(String engagementName) {
		this.engagementName = engagementName;
	}

	public String getRevenueInfelunceType() {
		return this.revenueInfelunceType;
	}

	public void setRevenueInfelunceType(String revenueInfelunceType) {
		this.revenueInfelunceType = revenueInfelunceType;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

}