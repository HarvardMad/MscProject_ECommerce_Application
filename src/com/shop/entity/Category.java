package com.shop.entity;

/**
 * Created by LalinPethiyagoda on 26/07/2015.
 */
import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="category")
@SecondaryTable(name="category")
@NamedQueries({
        @NamedQuery(name="Category.findAll",
                query="SELECT c " + "FROM Category c ")})
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CATEGORY_CATEGORYID_GENERATOR", sequenceName = "CATEGORY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_CATEGORYID_GENERATOR")
    private Integer categoryid;

    private String description;
    //bi-directional many-to-one association to ProductRemote
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<ProductEntity> products;

    public Category() {
    }

    public Integer getCategoryid() {
        return this.categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductEntity> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }


    public ProductEntity addProducts(ProductEntity products) {
        getProducts().add(products);
        products.setCategory(this);

        return products;
    }

    public ProductEntity removeProducts(ProductEntity products) {
        getProducts().remove(products);
        products.setCategory(null);

        return products;
    }
}