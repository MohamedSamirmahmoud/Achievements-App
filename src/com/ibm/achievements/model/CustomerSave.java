package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CUSTOMERSAVES database table.
 * 
 */
@Entity
@Table(name="CUSTOMERSAVES")
@NamedQuery(name="CustomerSave.findAll", query="SELECT c FROM CustomerSave c")
public class CustomerSave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String brand;

	private String businessUnits;

	private String country;

	private String customerName;

	private String customerProblem;

	private String employeeContribution;

	private String engagementName;

	//uni-directional one-to-one association to Achievement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID", insertable=false , updatable=false)
	private Achievement achievement;

	public CustomerSave() {
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

	public String getCustomerProblem() {
		return this.customerProblem;
	}

	public void setCustomerProblem(String customerProblem) {
		this.customerProblem = customerProblem;
	}

	public String getEmployeeContribution() {
		return this.employeeContribution;
	}

	public void setEmployeeContribution(String employeeContribution) {
		this.employeeContribution = employeeContribution;
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