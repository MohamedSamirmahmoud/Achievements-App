package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FEEDBACKTODEVELOPMENT database table.
 * 
 */
@Entity
@NamedQuery(name="FeedbackToDevelopment.findAll", query="SELECT f FROM FeedbackToDevelopment f")
public class FeedbackToDevelopment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String brand;

	private String businessUnits;

	private int pmrNumber;

	private String product;

	private String typeOfFeedback;

	//uni-directional one-to-one association to Achievement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID", insertable=false , updatable=false)
	private Achievement achievement;

	public FeedbackToDevelopment() {
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

	public String getBusinessUnits() {
		return this.businessUnits;
	}

	public void setBusinessUnits(String businessUnits) {
		this.businessUnits = businessUnits;
	}

	public int getPmrNumber() {
		return this.pmrNumber;
	}

	public void setPmrNumber(int pmrNumber) {
		this.pmrNumber = pmrNumber;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getTypeOfFeedback() {
		return this.typeOfFeedback;
	}

	public void setTypeOfFeedback(String typeOfFeedback) {
		this.typeOfFeedback = typeOfFeedback;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

}