package com.shop.entity;

/**
 * Created by LalinPethiyagoda on 30/07/2015.
 */
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * The persistent class for the order database table.
 *
 */
@Entity
@Table(name= "tblorder")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="ORDER_ORDERID_GENERATOR", sequenceName="ORDER_ORDERID_SEQ", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDER_ORDERID_GENERATOR")
    private Integer orderid;

    @Temporal(TemporalType.DATE)
    private Date orderdate;

   @ManyToOne(cascade=CascadeType.ALL)
    //@JoinColumn(name="customerid")
    private CustomerEntity customer;

    //bi-directional many-to-one association to Orderitem
    @OneToMany(cascade = CascadeType.ALL, mappedBy="order")
    private List<Orderitem> orderitems;

    @PrePersist
    protected void onCreate() {
        orderdate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        orderdate = new Date();
    }

    public Order() {
    }

    public Integer getOrderid() {
        return this.orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }


    public CustomerEntity getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerEntity customer) {
        System.out.println("Im liked to the order " + customer.getName());
        this.customer = customer;
        //*******
        this.customer.getOrders().add(this);
    }

    public Date getOrderdate() {
        return this.orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public List<Orderitem> getOrderitems() {
        return this.orderitems;
    }

    public void setOrderitems(List<Orderitem> orderitems) {
        System.out.println("xxxxx " + orderitems.size());
        this.orderitems = orderitems;
    }

    public Orderitem addOrderitems(Orderitem orderitems) {
        getOrderitems().add(orderitems);
        //orderitems.setOrder(this);

        return orderitems;
    }

    public Orderitem removeOrderitems(Orderitem orderitems) {
        getOrderitems().remove(orderitems);
        orderitems.setOrder(null);

        return orderitems;
    }

    //@ManyToOne(optional = false)
   // private CustomerEntity customerEntities;

    //public CustomerEntity getCustomerEntities() {
       // return customerEntities;
    //}

    //public void setCustomerEntities(CustomerEntity customerEntities) {
        //this.customerEntities = customerEntities;
    //}
}