package com.shop.managedbeans;

import com.shop.cart.CartFacade;
import com.shop.entity.CustomerManagerLocal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="login")
@SessionScoped
public class Login {
    @EJB
    CustomerManagerLocal customerManager;

    @EJB
    CartFacade facade;
    private String email;
    private String password;


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

    public String verifyCustomer(){


        String result;
        result= customerManager.verifyCustomer(this.email, this.password);
        return result;
    }


}