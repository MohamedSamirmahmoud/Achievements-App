package com.ibm.achievements.model;

import java.io.Serializable;
import javax.persistence.*;
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
	private List<Achievement> achievements;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="EMPLOYEEMANAGERID")
	private Employee employee;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="employee")
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

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setEmployee(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setEmployee(null);

		return employee;
	}

}