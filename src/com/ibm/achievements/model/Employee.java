package com.ibm.achievements.model;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the EMPLOYEE database table.
 * 
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int employeeId;

	private int employeeIsManager;

	private String employeeMail;

	private String employeeName;

	private String employeePassword;

	private int markedAsDeleted;

	//bi-directional many-to-many association to Achievement
	@ManyToMany
	@JoinTable(
		name="EMPLOYEE_ACHIEVEMENT"
		, joinColumns={
			@JoinColumn(name="EMPLOYEEID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ACHIEVEMENTID")
			}
		)
	@JsonBackReference
	private List<Achievement> achievements;

	//bi-directional many-to-one association to Employee
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="EMPLOYEEMANAGERID")
	private Employee manager;

	//bi-directional many-to-one association to Employee
	@JsonBackReference
	@OneToMany(mappedBy="manager")
	private List<Employee> employees;

	public Employee() {
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getEmployeeIsManager() {
		return this.employeeIsManager;
	}

	public void setEmployeeIsManager(int employeeIsManager) {
		this.employeeIsManager = employeeIsManager;
	}

	public String getEmployeeMail() {
		return this.employeeMail;
	}

	public void setEmployeeMail(String employeeMail) {
		this.employeeMail = employeeMail;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePassword() {
		return this.employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public int getMarkedAsDeleted() {
		return this.markedAsDeleted;
	}

	public void setMarkedAsDeleted(int markedAsDeleted) {
		this.markedAsDeleted = markedAsDeleted;
	}

	public List<Achievement> getAchievements() {
		return this.achievements;
	}

	public void setAchievements(List<Achievement> achievements) {
		this.achievements = achievements;
	}

	public Employee getManager() {
		return this.manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setManager(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setManager(null);

		return employee;
	}

}