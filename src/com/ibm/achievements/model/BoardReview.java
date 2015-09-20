package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BOARDREVIEWS database table.
 * 
 */
@Entity
@Table(name="BOARDREVIEWS")
@NamedQuery(name="BoardReview.findAll", query="SELECT b FROM BoardReview b")
public class BoardReview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String boardReviewLevel;

	private int flag;

	private String reviewType;

	private String typeOfCertificate;

	//uni-directional one-to-one association to Achievement
	@OneToOne
	@JoinColumn(name="ACHIEVEMENTID"  , insertable=false , updatable=false)
	private Achievement achievement;

	public BoardReview() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementid) {
		this.achievementId = achievementid;
	}

	public String getBoardReviewLevel() {
		return this.boardReviewLevel;
	}

	public void setBoardReviewLevel(String boardReviewLevel) {
		this.boardReviewLevel = boardReviewLevel;
	}

	public int getFlag() {
		return this.flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getReviewType() {
		return this.reviewType;
	}

	public void setReviewType(String reviewType) {
		this.reviewType = reviewType;
	}

	public String getTypeOfCertificate() {
		return this.typeOfCertificate;
	}

	public void setTypeOfCertificate(String typeOfCertificate) {
		this.typeOfCertificate = typeOfCertificate;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

}