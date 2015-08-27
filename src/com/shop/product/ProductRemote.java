package com.shop.product;

/**
 * Created by LalinPethiyagoda on 25/07/2015.
 */

import java.util.List;
import javax.ejb.Remote;

import com.shop.entity.Category;
import com.shop.entity.ProductEntity;

@Remote
public interface ProductRemote  {
    void persistProduct(Object newProduct);
    public List<ProductEntity> getProductsList();
    List<ProductEntity> getProductByCategory(Category category);
    List<Category> getCategoryList();
}