package com.shop.product;

/**
 * Created by LalinPethiyagoda on 25/07/2015.
 */


import java.util.List;
import javax.ejb.Local;

import com.shop.entity.Category;
import com.shop.entity.ProductEntity;

@Local
public interface ProductLocal {
    void persistProduct(Object newProduct);
    public List<ProductEntity> getProductsList();
    List<ProductEntity> getProductByCategory(Category category);
    List<Category> getCategoryList();


}
