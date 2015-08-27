package com.shop.product;

/**
 * Created by LalinPethiyagoda on 25/07/2015.
 */

import javax.ejb.Stateless;
        import javax.persistence.EntityManager;
        import javax.persistence.PersistenceContext;
        import com.shop.entity.ProductEntity;
        import java.io.Serializable;
        import java.util.List;
        import com.shop.entity.*;

@Stateless(name="ProductLocal")
public class ProductFacade implements ProductRemote, ProductLocal, Serializable {

    @PersistenceContext(unitName ="Shop")
    private EntityManager  entityManagerForProducts;
    private static final long serialVersionUID = 1L;
    public ProductFacade(){}

    public void persistProduct(Object newProduct){
        entityManagerForProducts.merge(newProduct);

    }
    @Override
    public List<ProductEntity> getProductsList(){

        return entityManagerForProducts.createNamedQuery("ProductEntity.findAll",ProductEntity.class)
                .getResultList();
    }
    @Override
    public List<Category> getCategoryList(){
        return entityManagerForProducts.createNamedQuery("Category.findAll",Category.class)
                .getResultList();
    }

    @Override
    public List<ProductEntity> getProductByCategory(Category category) {
        return entityManagerForProducts.createNamedQuery("ProductEntity.findByCategory",ProductEntity.class)
                .setParameter("category", category)
                .getResultList();
    }

}