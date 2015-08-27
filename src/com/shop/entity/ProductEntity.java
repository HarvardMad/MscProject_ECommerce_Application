package com.shop.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the product database table.
 *
 */
@Entity(name="ProductEntity")
@Table(name="tblproduct")
//SecondaryTable(name="product")
@NamedQueries({
        @NamedQuery(name="ProductEntity.findAll",
                query="SELECT p " + "FROM ProductEntity p"),
        @NamedQuery(name="ProductEntity.findByCategory",
                query="SELECT p " + "FROM ProductEntity p " + "WHERE p.category= :category")})


@SequenceGenerator(name="ProductSequence",
        sequenceName="product_seq",
        initialValue=1, allocationSize=1)

public class ProductEntity implements Serializable {


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((productid == null) ? 0 : productid.hashCode());
        return result;
    }

    public ProductEntity(){}
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductEntity other = (ProductEntity) obj;
        if (productid == null) {
            if (other.productid != null)
                return false;
        } else if (!productid.equals(other.productid))
            return false;
        return true;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="productid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ProductSequence")
    private Integer productid;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="itemcode")
    private String itemcode;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="itemdescription")
    private String itemdescription;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="unitprice")
    private double unitprice;

    @Basic(fetch=FetchType.EAGER)
    @Column(name= "quantityinstock")
    private Integer quantityinstock;



    //bi-directional many-to-one association to Category
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="categoryid")
    private Category category;

    public Integer getProductid() {
        return this.productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getItemcode() {
        return this.itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemdescription() {
        return this.itemdescription;
    }

    public void setQuantityinstock(int quantityinstock) {
        this.quantityinstock = quantityinstock;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public double getUnitprice() {return this.unitprice;}

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getQuantityinstock() {return quantityinstock;}

    public void setCategory(Category category) {
        this.category = category;
    }
    public Category getCategory() {
        return category;
    }
}
