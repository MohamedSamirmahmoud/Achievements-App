package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HIGHIMPACTASSET database table.
 * 
 */
@Entity
@NamedQuery(name="HighImpactAsset.findAll", query="SELECT h FROM HighImpactAsset h")
public class HighImpactAsset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String brand;

	private String description;

	private String highImpactAssetName;

	private String typeOfAsset;

	private String typeOfAsset2;

	//uni-directional one-to-one association to Achievement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID", insertable=false , updatable=false)
	private Achievement achievement;

	public HighImpactAsset() {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHighImpactAssetName() {
		return this.highImpactAssetName;
	}

	public void setHighImpactAssetName(String highImpactAssetName) {
		this.highImpactAssetName = highImpactAssetName;
	}

	public String getTypeOfAsset() {
		return this.typeOfAsset;
	}

	public void setTypeOfAsset(String typeOfAsset) {
		this.typeOfAsset = typeOfAsset;
	}

	public String getTypeOfAsset2() {
		return this.typeOfAsset2;
	}

	public void setTypeOfAsset2(String typeOfAsset2) {
		this.typeOfAsset2 = typeOfAsset2;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

}