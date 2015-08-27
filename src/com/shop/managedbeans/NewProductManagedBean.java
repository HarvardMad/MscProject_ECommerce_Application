package com.shop.managedbeans;

/**
 * Created by LalinPethiyagoda on 25/07/2015.
 */
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import com.shop.entity.*;
import com.shop.product.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.List;

//CDI annotations
@ManagedBean(name="newProduct")
@SessionScoped
public class NewProductManagedBean {
    @EJB
    ProductLocal productFacadeBean;

    private String itemCode;
    private String itemDescription;
    private double unitPrice;
    private String size;
    private int quantity;
    private List<Category> categoryList=null;
    private Category category;
    private int reOrderLevel;
    private Category selectedCategory;



    public Category getSelectedCategory() {
        return selectedCategory;
    }
    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    @PostConstruct
    private void init(){


        getCategories();
    }


    public String setUpCategoryToGetProductsList(Category category){
        String navigation = null;
        setCategory(category);
        if(getCategory()!=null){

        }
        navigation = "Product";

        return navigation;
    }
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemcode) {
        this.itemCode = itemcode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public String addNewProduct() {
        ProductEntity newProd = new ProductEntity();
        System.out.println("hello " + getCategory().getDescription());
        newProd.setItemcode(this.itemCode);
        newProd.setItemdescription(this.itemDescription);
        newProd.setUnitprice(this.unitPrice);
        newProd.setQuantityinstock(this.quantity);
        newProd.setCategory(this.category);
        productFacadeBean.persistProduct(newProd);
        System.out.println("done");
        return "success";
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQunatity() {
        return this.quantity;
    }

    public void setQunatity(int quantity) {
        this.quantity = quantity;
    }

    public void getCategories(){
        categoryList=productFacadeBean.getCategoryList();
    }




}