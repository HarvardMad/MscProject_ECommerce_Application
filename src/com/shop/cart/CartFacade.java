package com.shop.cart;

/**
 * Created by LalinPethiyagoda on 30/07/2015.
 */

import com.shop.entity.CustomerEntity;
import com.shop.entity.Orderitem;
import com.shop.entity.ProductEntity;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Stateful
@SessionScoped
public class CartFacade {
    private CopyOnWriteArrayList<Orderitem>myCartList ;
   // @PersistenceContext(unitName ="Shop")
    //EntityManager cartEntityManager;

    public List<Orderitem> getCart() {
        return cart;
    }
    public void setCart(List<Orderitem> cart) {
        this.cart = cart;
    }
    List<Orderitem> cart = new ArrayList<>();

    /**
     * A client class can access the Stateful Session Bean using CDI via the @EJB annotation.
     *
     * The actual "link" between the physical customer
     * and its instance of the ShoppingCart implementation instance is assured because each client is associated
     * with a new instance of a stateful session bean. From the client's perspective, the business methods appear to run locally,
     * although they run remotely in the session bean. For the record...Oracle in their own tutorials suggests this approach
     *
     * Notice the user of the Remote interface to reference the cart
     */

    @EJB
    ShoppingCart cartBean;

   // Order newOrder = new Order();

    public CartFacade() {

    }
    public void assignCustomerToCart(CustomerEntity LoggedInCustomer){
        cartBean.assignCartToOrder(LoggedInCustomer);
    }

    public boolean addItemToCart(ProductEntity product, int quantityPurchased){
        boolean result = false;
        result=cartBean.addCartItem(product, quantityPurchased);
        System.out.println("Result from cart facade " + result);
        return result;
    }
    public List<Orderitem> viewItemsIntheCart(){
        System.out.println("cart facade " + cartBean.getCartItems().size());
        return cartBean.getCartItems();
    }
    public double getCartTotal(){
        return cartBean.getTotal();
    }

public String removeItemFromCart(String itemCode){
     return cartBean.removeItemFromCart(itemCode);
}

    public void persistOrder(){
        //cartBean.persistCartItems();

    }

}
