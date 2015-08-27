package com.shop.managedbeans;

/**
 * Created by LalinPethiyagoda on 30/07/2015.
 */



import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import com.shop.cart.CartFacade;
import com.shop.entity.*;

@ManagedBean(name="myCart")
@SessionScoped
public class ShoppingCartManagedBean {


    com.shop.cart.CartFacade cartBean = new CartFacade();

    com.shop.entity.ProductEntity product;

    // variable list
    private Integer orderitemid;
    private String itemcode;
    private String itemdescription;
    private Integer quantityPurchased;
    private String size;
    private double subtotal;
    private double unitprice;

    @PostConstruct
    private void init(){
        product= new ProductEntity();
    }

    public void addItemToCart(){
        product.setItemcode(itemcode);
        product.setItemdescription(itemdescription);
        product.setUnitprice(unitprice);
            cartBean.addItemToCart(product, quantityPurchased);
        System.out.println("item added " + product.toString());
    }

    public Integer getOrderitemid() {
        return orderitemid;

    }
    public void setOrderitemid(Integer orderitemid) {
        this.orderitemid = orderitemid;
    }
    public String getItemcode() {
        return itemcode;
    }
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }
    public String getItemdescription() {
        return itemdescription;
    }
    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }
    public Integer getQuantitypurchased() {
        return quantityPurchased;
    }
    public void setQuantitypurchased(Integer quantitypurchased) {
        this.quantityPurchased = quantitypurchased;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    public double getUnitprice() {
        return unitprice;
    }
    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }
}