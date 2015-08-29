package com.shop.cart;

/**
 * Created by LalinPethiyagoda on 30/07/2015.
 */


        import com.shop.entity.CustomerEntity;
import com.shop.entity.Orderitem;
import com.shop.entity.ProductEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ShoppingCartLocal {
    List<Orderitem> viewCartItems();
    void persistCartItems(CustomerEntity c);
    public List<Orderitem> getCartItems();
    public boolean addCartItem(ProductEntity product, int quantityPurchased);
    void removeCartItem(int itemCode);
    public void assignCartToCustomer();
    public CustomerEntity getCustomerAssociatedWithTheCart();
    public double getTotal();
    public void remove();
    public void incrementQuantity();
    public void decrementQuantity();
    String removeItemFromCart(String itemCode);
    public void assignCartToOrder(CustomerEntity customer);

}