package com.ibm.achievements.model;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonManagedReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ACHIEVEMENT database table.
 * 
 */
@Entity
@NamedQuery(name="Achievement.findAll", query="SELECT a FROM Achievement a")
public class Achievement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int achievementId;

	@Temporal(TemporalType.DATE)
	private Date achievementDate;

	private String achievementType;

	@Column(name="\"COMMENT\"")
	private String comment;

	private String lobName;

	private int markedAsDeleted;

	@Column(name="\"STATUS\"")
	private String status;

	//bi-directional many-to-many association to Employee
	@JsonManagedReference
	@ManyToMany(mappedBy="achievements")
	private List<Employee> employees = new ArrayList<Employee>();

	public Achievement() {
	}

	public int getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}

	public Date getAchievementDate() {
		return this.achievementDate;
	}

	public void setAchievementDate(Date achievementDate) {
		this.achievementDate = achievementDate;
	}

	public String getAchievementType() {
		return this.achievementType;
	}

	public void setAchievementType(String achievementType) {
		this.achievementType = achievementType;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLobName() {
		return this.lobName;
	}

	public void setLobName(String lobName) {
		this.lobName = lobName;
	}

	public int getMarkedAsDeleted() {
		return this.markedAsDeleted;
	}

	public void setMarkedAsDeleted(int markedAsDeleted) {
		this.markedAsDeleted = markedAsDeleted;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}