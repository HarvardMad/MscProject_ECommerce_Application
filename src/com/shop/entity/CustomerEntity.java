
package com.shop.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * @author Lalin
 *
 */
@Entity
@Table(name= "tblcustomer")
//@SecondaryTable(name="tblCustomer")
@NamedQueries({
        @NamedQuery(name="CustomerEntity.CustomerFindbyEmailAndPassword",
                query="SELECT c " + "FROM CustomerEntity c " + "WHERE c.eMail= :email AND " + "c.myPassword= :password" )})
/**identifies the class as an entity at the time 
 * of deployment
 * The annotation allows the entity class 
 * to take part in queries, updates and deletes and 
 * object relational mapping
 * */

@SequenceGenerator(name="CustomerSequence",
        sequenceName="cust_sequence",
        initialValue=1, allocationSize=1)
public class CustomerEntity implements Serializable {

    public CustomerEntity(){} // non arg constructor - can have others
    /** @Id identifies customerId as PK, can have several
     * @EbeddedId can also be used to represent composite keys:
     */

    @Id
    @Column(name="customerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="CustomerSequence")
    private int CustomerId;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="name")
    private String Name;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="telephone")
    private String Telephone;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="mobilephone")
    private String MobilePhone;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="email")
    private String eMail;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="password")
    private String myPassword;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="houseno")
    private String houseNo;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="street")
    private String street;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="town")
    private String town;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="postcode")
    private String postCode;

    @Basic(fetch=FetchType.EAGER)
    @Column(name="county")
    private String county;

    @OneToMany(mappedBy="customer", cascade={CascadeType.ALL})
    List<Order> order;

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    private static final long serialVersionUID = 1L;

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        this.CustomerId= customerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        this.Telephone = telephone;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.MobilePhone = mobilePhone;
    }

    public void setEmail(String email) {
        this.eMail=email;

    }

    public String getEmail(){
        return this.eMail;
    }


    public void setPassword(String Password){
        this.myPassword=Password;

    }


    public List<Order> getOrders() {
        return order;
    }

    public void setOrders(List<Order> orders) {

        this.order=orders;
    }


}
