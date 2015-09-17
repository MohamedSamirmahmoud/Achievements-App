package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENABLEMENTCUSTOMER database table.
 * 
 */
@Entity
@NamedQuery(name="EnablementCustomer.findAll", query="SELECT e FROM EnablementCustomer e")
public class EnablementCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String customerName;

	private String customerType;

	//uni-directional one-to-one association to Enablement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID")
	private Enablement enablement;

	public EnablementCustomer() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}


	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public Enablement getEnablement() {
		return this.enablement;
	}

	public void setEnablement(Enablement enablement) {
		this.enablement = enablement;
	}

}