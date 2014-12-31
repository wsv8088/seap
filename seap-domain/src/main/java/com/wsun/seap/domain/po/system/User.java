package com.wsun.seap.domain.po.system;

/**
 * Created by Administrator on 2014/10/5 0005.
 */
public class User {

	private Integer id;

	private String company;

	private String department;

	private String loginName;

	private String password;

	private String email;

	private String realName;

	private String phone;

	private String mobile;

	private int userType;

	private String loginIp;

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public String getCompany () {
		return company;
	}

	public void setCompany (String company) {
		this.company = company;
	}

	public String getDepartment () {
		return department;
	}

	public void setDepartment (String department) {
		this.department = department;
	}

	public String getLoginName () {
		return loginName;
	}

	public void setLoginName (String loginName) {
		this.loginName = loginName;
	}

	public String getPassword () {
		return password;
	}

	public void setPassword (String password) {
		this.password = password;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail (String email) {
		this.email = email;
	}

	public String getRealName () {
		return realName;
	}

	public void setRealName (String realName) {
		this.realName = realName;
	}

	public String getPhone () {
		return phone;
	}

	public void setPhone (String phone) {
		this.phone = phone;
	}

	public String getMobile () {
		return mobile;
	}

	public void setMobile (String mobile) {
		this.mobile = mobile;
	}

	public int getUserType () {
		return userType;
	}

	public void setUserType (int userType) {
		this.userType = userType;
	}

	public String getLoginIp () {
		return loginIp;
	}

	public void setLoginIp (String loginIp) {
		this.loginIp = loginIp;
	}

}
