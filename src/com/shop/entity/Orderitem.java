package com.shop.entity;

/**
 * Created by LalinPethiyagoda on 30/07/2015.
 */
import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the orderitem database table.
 *
 */

@SequenceGenerator(name="ORDERITEM_ORDERITEMID_GENERATOR",
        sequenceName="ORDERITEM_ORDERITEMID_SEQ",
        initialValue=1, allocationSize=1)

@Entity
@Table(name= "orderitem")
public class Orderitem implements Serializable {
   // private static final long serialVersionUID = 1L;

    @Id
    @Column(name="orderitemid")
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="ORDERITEM_ORDERITEMID_GENERATOR")
    private Integer orderitemid;

    private String itemcode;
    private String itemdescription;
    private Integer quantitypurchased;
   // private String size;
    private double subtotal;
    private double unitprice;

    //bi-directional many-to-one association to Order
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="orderid")
    private Order order;

    //bi-directional many-to-one association to ProductRemote
    @ManyToOne
    @JoinColumn(name="productid")
    private ProductEntity product;

    public Orderitem() {
    }

    public Integer getOrderitemid() {
        return this.orderitemid;
    }

    public void setOrderitemid(Integer orderitemid) {
        this.orderitemid = orderitemid;
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

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public Integer getQuantitypurchased() {
        return this.quantitypurchased;
    }

    public void setQuantitypurchased(Integer quantitypurchased) {
        this.quantitypurchased = quantitypurchased;
    }


    public double getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getUnitprice() {
        return this.unitprice;
    }

    public void setUnitprice(double unitPrice) {
        this.unitprice = unitPrice;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public ProductEntity getProduct() {
        return this.product;
    }

    public void setProduct(ProductEntity productEntity) {
        this.product = productEntity;
    }


}