package com.shop.entity;

import javax.ejb.Local;
@Local

public interface CustomerManagerLocal {
	 void persistNewCustomer(Object newCustomer);
	 String verifyCustomer(String email, String password);
	CustomerEntity getCustomerFindbyEmailPassword(String email, String password);

	CustomerEntity getVerifiedCustomer();
}
