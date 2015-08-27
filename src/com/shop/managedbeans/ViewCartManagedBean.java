package com.shop.managedbeans;

/**
 * Created by LalinPethiyagoda on 31/07/2015.
 */
import com.shop.entity.Orderitem;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@ManagedBean(name="ViewCartManagedBean")
@SessionScoped
public class ViewCartManagedBean {
    List<Orderitem> cartItemsList = new CopyOnWriteArrayList<>();
    List cart = new ArrayList<Orderitem>();
    @EJB
    com.shop.cart.ShoppingCartLocal cartBean;

    public ViewCartManagedBean(){}

    @PostConstruct
    public void initialize(){
        setCartItemsList();
    }
    public List<Orderitem> getCartItemsList() {
        setCartItemsList();
        return cartItemsList;
    }

    public void setCartItemsList() {
        cartItemsList=null;
        System.out.println("Inside managed bean " + cartBean.getCartItems().size());
        cartItemsList = cartBean.getCartItems();
    }
}