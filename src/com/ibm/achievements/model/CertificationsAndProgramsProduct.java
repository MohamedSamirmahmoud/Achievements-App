package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name = "CertificationsAndProgramsProduct.findAll", query = "SELECT c FROM CertificationsAndProgramsProduct c")
public class CertificationsAndProgramsProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int achievementId;

	private String brand;

	private String busUnit;

	private String certificationExam;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	// uni-directional one-to-one association to CertificationsAndProgram
	@OneToOne
	@JoinColumn(name = "ACHIEVEMENTID")
	private CertificationsAndProgram certificationsandprogram;

	public CertificationsAndProgramsProduct() {
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

	public String getCertificationExam() {
		return this.certificationExam;
	}

	public void setCertificationExam(String certificationExam) {
		this.certificationExam = certificationExam;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public CertificationsAndProgram getCertificationsandprogram() {
		return this.certificationsandprogram;
	}

	public void setCertificationsandprogram(
			CertificationsAndProgram certificationsandprogram) {
		this.certificationsandprogram = certificationsandprogram;
	}

}