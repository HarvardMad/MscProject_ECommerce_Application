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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	private Date dob;
	

	//constructor
	public NewCustomer(){}

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
