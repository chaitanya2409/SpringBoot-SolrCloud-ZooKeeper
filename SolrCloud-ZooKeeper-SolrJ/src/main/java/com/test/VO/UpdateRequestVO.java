package com.test.VO;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.test.util.EncryptionTypeUtil;

@TypeDef(name = "encryptedString", typeClass = EncryptionTypeUtil.class)
public class UpdateRequestVO {
	
	private String id;
	
	@Type(type = "encryptedString")
	private String firstName;
	
	@Type(type = "encryptedString")
	private String lastName;
	
	private String userName;
	@Type(type = "encryptedString")
	private String workEmail;
	
	@Type(type = "encryptedString")
	private String phone1;
	
	@Type(type = "encryptedString")
	private String phone2;
	
	@Type(type = "encryptedString")
	private String address1;
	
	@Type(type = "encryptedString")
	private String address2;
	
	@Type(type = "encryptedString")
	private String companyName;
	
	@Type(type = "encryptedString")
	private String city;
	
	private String jobTitle;
	
	private String isDeleted;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getId() {
		return id;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
}
