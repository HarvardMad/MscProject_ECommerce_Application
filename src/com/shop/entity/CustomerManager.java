package com.shop.entity;

import javax.ejb.Remote;



@Remote
public interface CustomerManager {
	  void persistNewCustomer(Object newCustomer);
	 String verifyCustomer(String email, String password);

	CustomerEntity getVerifiedCustomer();
}
