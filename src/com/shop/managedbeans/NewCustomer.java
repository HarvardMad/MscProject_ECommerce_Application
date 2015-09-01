package com.shop.managedbeans;
import com.shop.entity.*;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.Date;

@ManagedBean(name="newCustomer")
public class NewCustomer {
	@EJB CustomerManagerLocal customerManager;
	
	private String name;
	private String telephone;
	private String mobilePhone;
	private String email;
	private String password;
	private String houseNo;
	private String street;
	private String town;
	private String postCode;
	private String county;
	private Date dob;

	//constructor
	public NewCustomer(){}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String addNewCustomer(){
		CustomerEntity newCustomer = new CustomerEntity();
		newCustomer.setName(this.name);
		newCustomer.setTelephone(this.telephone);
		newCustomer.setMobilePhone(this.mobilePhone);
		newCustomer.setEmail(this.email);
		newCustomer.setPassword(this.password);
		newCustomer.setHouseNo(this.houseNo);
		newCustomer.setStreet(this.street);
		newCustomer.setTown(this.town);
		newCustomer.setPostCode(this.postCode);
		newCustomer.setCounty(this.county);
		customerManager.persistNewCustomer(newCustomer);
		
		return "success";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
