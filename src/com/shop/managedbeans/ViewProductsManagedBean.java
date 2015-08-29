package com.shop.managedbeans;

/**
 * Created by LalinPethiyagoda on 27/07/2015.
 */

/**
 * The @EJB annotation (and @Resource, @WebServiceRef, etc.) serves two purposes:
 * It declares a reference in the component namespace. For example, @EJB(name="myEJB")
 * creates a reference java:comp/env/myEJB. If you annotate a field and do not specify a name, then it creates a reference java:comp/env/com.example.MyClass/myField.
 * If the annotation is declared on a field or setter method, then the container performs injection when the component is created.
 * How the reference is resolved varies, independent of whether the reference is being resolved for a lookup("java:comp/env/myEJB") or due to injection:
 * If EE 6+ is used, the lookup attribute requires a JNDI lookup to resolve the target.
 * Some application servers support mappedName, which is specified to be vendor specific. This is usually implemented by performing a lookup.
 * Application servers support bindings at deployment time. This is usually implemented by performing a lookup.
 * If no other binding information is provided and the bean interface (beanInterface or the field type) is only implemented by a single EJB in the application, then the EJB specification requires that it fall back to that.
 * If no other binding information is provided and #4 cannot work,
 * some application servers will attempt to perform a lookup in the server namespace based on the ref name (for example, java:comp/env/myEJB might cause a lookup of myEJB in the server namespace).
 *
 */

import com.shop.entity.Category;
import com.shop.entity.CustomerEntity;
import com.shop.entity.Orderitem;
import com.shop.entity.ProductEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.shop.cart.ShoppingCartBean;
        //import com.shop.entity.StockEntity;
//import com.shop.product.ProductLocal;

@ManagedBean(name="ViewProductsManagedBean")
@RequestScoped
public class ViewProductsManagedBean {

    private double unitprice;
    private String itemdescription;
    private String itemcode;
    private int quantityPurchased;
    private String result;
    private double cartTotal;
    private List<Orderitem> cart = new ArrayList<>();
    private ProductEntity selectedProduct;
    private Category category;
    private ProductEntity product;
    private Map<ProductEntity, Integer> productsAndTheirQuanitities = new HashMap<>();
    private List<ProductEntity> productsList = new ArrayList<>();



    @EJB
    com.shop.product.ProductLocal productFacadeBean;

    @EJB
    com.shop.cart.CartFacade cartFunctions;

    @EJB
    com.shop.entity.CustomerManagerLocal customerManagerBean;

    public ViewProductsManagedBean() {

    }

    public double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(double cartTotal) {
        this.cartTotal = cartTotal;
    }


    public List<Orderitem> getCart() {
        return cart;
    }

    public void setCart(List<Orderitem> cart) {
        this.cart = cart;
    }

    public ProductEntity getProduct() {
        return product;
    }
    public void setProduct(ProductEntity product) {
        this.product = product;
    }
    public ProductEntity getSelectedProduct() {
        return selectedProduct;
    }
    public void setSelectedProduct(ProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    public Category getCategory() {

        return category;
    }
    public void setCategory(Category category) {

        this.category = category;
    }



    /**
     * A managed bean is created using its no
     * args constructor
     * The main functions of the Managed Bean include
     * handling events fired by components
     * forwarding to the correct page
     * data validation
     * <p>
     * Deferred evaluation of expressions is important because the
     * JavaServer Faces lifecycle is split into several phases
     * in which component event handling, data conversion and validation,
     * and data propagation to external objects are all performed in an orderly fashion.
     * The implementation must be able to delay the evaluation of expressions until the proper phase of the lifecycle has been reached.
     * Therefore,the implementation’s tag attributes always
     * use deferred-evaluation syntax, which is distinguished by the #{} delimiter.
     * <p>
     * To store data in external objects, almost all JavaServer Faces tag attributes
     * use lvalue expressions, which are expressions that allow both getting and setting data on external objects.
     */


    @PostConstruct
    private void init() {
        //setProductsList();

    }

    public String setUpCategoryToGetProductsList(Category category){
        String navigation = null;
        this.category=null;
        setCategory(category);
        if(getCategory()!=null){
            setProductsList();
        }
        navigation = "Product";

        return navigation;
    }
    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public String addItemToCart(ProductEntity product) {
        int quantity = 0;
        boolean result = false;
        String navigation = null;
        if (productsAndTheirQuanitities.containsKey(product)) {
            quantity = productsAndTheirQuanitities.get(product);
            System.out.println("selected quantity : " + quantity);
            System.out.println("selected product : " + product.getItemdescription());

        }

        result = cartFunctions.addItemToCart(product, quantity);
        setCartTotal(cartFunctions.getCartTotal());

        if (result == true) {
            setCart(cartFunctions.viewItemsIntheCart());
            navigation = "ViewCart";
        } else {
            navigation = "Product";

        }
        productsAndTheirQuanitities.clear();
        return navigation;

    }

    public String removeItemFromCart(String itemCode){
        return cartFunctions.removeItemFromCart(itemCode);
    }


    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public String getItemcode() {
        return this.itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public void setProductsList() {
        productsList = productFacadeBean.getProductByCategory(this.category);
    }

    public List<ProductEntity> getProductsList() {

        return this.productsList;
    }

    public String getResult() {

        return result;
    }

    public void setResult(String result) {

        this.result = result;
    }

    public Map<ProductEntity, Integer> getProductsAndTheirQuanitities() {
        return productsAndTheirQuanitities;
    }

    public void setProductsAndTheirQuanitities(
            Map<ProductEntity, Integer> productsAndTheirQuanitities) {
        this.productsAndTheirQuanitities = productsAndTheirQuanitities;
    }

    public void persistOrder(String email, String password){
        CustomerEntity cust =customerManagerBean.getCustomerFindbyEmailPassword(email, password);
        cartFunctions.persistOrder(cust);

    }

}

