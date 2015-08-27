/**
	 * This stateless session bean uses an 
	 * injected EntityManager instance to
	 * persist a new Customer instance
	 * *************************************************************************
	 * The EntityManager is the client's gateway to 
	 * entity management services offered by the JPA 
	 * persistence framework. 
	 * The client sessions must obtain an EM instance
	 * before interacting with persistent
	 * entity instances.
	 ***************************************************************************
	 * In this instance, the EntityManager instance 
	 * is bound to the "Shop" persistence unit (in persistence.xml) 
	 * which includes a reference to Customer.java- shown below:
	 * "<class>com.shop.entity.Customer</class>"
	 * There may be occasions when such injections 
	 * are not an option:when you need 
	 * more control over the life cycle of the EntityManager
	 * In such cases, the CLIENT can obtain an EntityManager 
	 * by first acquiring an EntityManagerFactory and this is done
	 * inside a main method in the client class
	 * ***************************************************************************
	 * Persistent units can be packaged as part of a WAR or EJB JAR file or can be 
	 * packaged as a JAR file that can then be included in an WAR or EAR file.
	 * If you package the persistent unit as a set of classes in an EJB JAR file, 
	 * persistence.xml should be put in the EJB JAR�s META-INF directory.
	 * If you package the persistence unit as a set of classes in a WAR file, 
	 * persistence.xml should be located in the WAR file�s WEB-INF/classes/META-INF directory.
	 * If you package the persistence unit in a JAR file that will be included 
	 * in a WAR or EAR file, the JAR file should be located in either
	 * The WEB-INF/lib directory of a WAR
	 * 
	 * The EntityManager 
	 * @author Lalin
	 */

package com.shop.entity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Session Bean FACADE implementation class CustomerManagerBean
 */
@Stateless

public class CustomerManagerBean implements CustomerManager, CustomerManagerLocal{
	
	@PersistenceContext(unitName ="Shop")
    private EntityManager  entityManager;

	@EJB
	com.shop.cart.CartFacade cartFacade;


	CustomerEntity customer;
	public CustomerManagerBean() {

	}

	@Override
	public void persistNewCustomer(Object newCustomer) {
		this.customer= (CustomerEntity)newCustomer;
		System.out.println(customer.getName());
		entityManager.persist(newCustomer);	
	}


	@Override
	public String verifyCustomer(String email, String password){
		String navigation;

		List<CustomerEntity> verifiedUser=getCustomerFindbyEmailAndPassword(email,password);
		if (verifiedUser.size()>0){
			customer =getCustomerFindbyEmailPassword(email,password);
			cartFacade.assignCustomerToCart(customer);

			System.out.println("hey " + customer.toString());

			navigation="verified";
		}
		else{
			navigation ="register";
		}
		return navigation;
	}

	public List<CustomerEntity> getCustomerFindbyEmailAndPassword(String email, String password){
		return entityManager.createNamedQuery("CustomerEntity.CustomerFindbyEmailAndPassword",CustomerEntity.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.getResultList();
		
	}
	
	public CustomerEntity getCustomerFindbyEmailPassword(String email, String password){
		return entityManager.createNamedQuery("CustomerEntity.CustomerFindbyEmailAndPassword",CustomerEntity.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.getSingleResult();
		
	}
	public CustomerEntity getVerifiedCustomer(){
		return this.customer;
	}
}
