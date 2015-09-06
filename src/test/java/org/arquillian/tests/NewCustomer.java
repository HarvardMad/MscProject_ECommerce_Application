package org.arquillian.tests;

/**
 * Created by LalinPethiyagoda on 06/09/2015.
 */
import com.shop.entity.*;

import javax.inject.Inject;

public class NewCustomer {
    private CustomerEntity customerEntity;
    @Inject
    public NewCustomer(CustomerEntity cust){
        this.customerEntity=cust;
    }
}
