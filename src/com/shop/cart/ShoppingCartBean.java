package com.shop.cart;

/**
 * Created by LalinPethiyagoda on 30/07/2015.
 */

import com.shop.entity.*;

import javax.annotation.PreDestroy;
import javax.ejb.*;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
@Stateful
@Local(ShoppingCartLocal.class)
@Remote(ShoppingCart.class)
@SessionScoped
public class ShoppingCartBean implements ShoppingCartLocal, ShoppingCart {
    @PersistenceContext(unitName ="Shop")
    private EntityManager cartEntityManager;
    @EJB
    private CustomerManager customerManagerBean;
    private  CustomerEntity logginInCust;
    private CopyOnWriteArrayList<Orderitem> cartItems = new CopyOnWriteArrayList<>();
    private Order newOrder= new Order();
    List<Order> orders=new ArrayList<>();
    private int customerID =0;
    public ShoppingCartBean(){}

    public void setCartItems(CopyOnWriteArrayList<Orderitem> cartItems) {
        this.cartItems = cartItems;
    }

    public Order getNewOrder(){
        return this.newOrder;
    }
//******************************************************************************************//
    public void assignCartToOrder(CustomerEntity cust){
        customerID=cust.getCustomerId();
        logginInCust = cartEntityManager.find(CustomerEntity.class, customerID);
        //logginInCust.getOrders().add(newOrder);
        //newOrder.setCustomer(logginInCust);

    }
//*******************************************************************************************//
    @Override
    public boolean addCartItem(ProductEntity product, int quantityPurchased){
        com.shop.entity.Orderitem basketItem=new Orderitem();

        Locale currentLocale = new Locale("en", "GB");
        double subTotal;
        for (Orderitem itemsIntheCart : cartItems) {
            if (itemsIntheCart.getItemcode().equals(product.getItemcode())) {
                    return false;
            }
        }
            // now that we're sure that the item doesn't already exist in the basket
            // lets add the Orderitem to the cart
        System.out.println("order entity " + newOrder.toString());
        basketItem.setItemcode(product.getItemcode());
        basketItem.setItemdescription(product.getItemdescription());
        basketItem.setUnitprice(product.getUnitprice());
        basketItem.setQuantitypurchased(quantityPurchased);
        basketItem.setProduct(product);
        //newOrder.setCustomer(logginInCust);
        System.out.println("Inside  addCartItems " + newOrder.toString());
        //basketItem.setOrder(newOrder);

            subTotal = quantityPurchased * basketItem.getUnitprice();
            Double currencyAmount = new Double(subTotal);
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
            currencyFormatter.format(currencyAmount);
            basketItem.setSubtotal(currencyAmount);
            cartItems.add(basketItem);
            return true;
    }
    public String removeItemFromCart(String itemCode){
        for(Orderitem ord:cartItems){
            if(itemCode.equals(ord.getItemcode())){
                cartItems.remove(ord);
                System.out.println("Item removed " + itemCode);
            }
        }
        return "ViewCart";
    }
@Override
    public CopyOnWriteArrayList<Orderitem> viewCartItems(){
    return this.cartItems;
    }
    @Override
    public CopyOnWriteArrayList<Orderitem> getCartItems(){
        CopyOnWriteArrayList<Orderitem> cartItemList = this.cartItems;
        for(Orderitem x:cartItemList){
            System.out.println(x.getItemdescription());
        }
        return cartItemList;
    }
    public void removeCartItem(int itemCode){
        System.out.println("hello");
    }

    @Override
    public double getTotal(){
        double total=0;
        for(Orderitem bItem:getCartItems()){
            total = total + bItem.getSubtotal();
        }
        return total;
    }

    @PreDestroy
    public void exit(){
        stopSession();
    }
    @Override
    public void persistCartItems() {

        //getNewOrder().setOrderitems(this.cartItems);
        //cartEntityManager.persist(newOrder);
        //cartEntityManager.flush();
    }
    @Remove
    public void stopSession(){
        System.out.println("saved- all done - object detached from object pool");
    }

    /**
     * the method acquireCustomerForShoppingCart in the
     * ShoppingCartBean calls the getVerifiedCustomer
     * method in the CustomerFacadeBean
     * Once the customer is acquired, it is assigned to a class level
     * attribute, customer, so that it can be referenced through the other methods
     * on this stateful session bean
     * Because we are using and EXTENDED persistence context, this instance
     * will remain managed throughout the life of the bean,
     * through transaction boundaries, unless it is actively
     * removed from the persistence context
     *
     * @returnmobilephone
     */
    public void assignCartToCustomer(){
        //customer = customerManagerBean.getVerifiedCustomer();
       // System.out.println("inside the shopping cart bean checking for assigned customer " + customer.getName());
    }


    public CustomerEntity getCustomerAssociatedWithTheCart(){
        return logginInCust;
    }
    @Remove
    public void remove() {
        cartItems = null;
    }

    @Override
    public void incrementQuantity() {
    }
    @Override
    public void decrementQuantity() {
    }

}
