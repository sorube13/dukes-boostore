package javaeetutorial.dukesbookstore.ejb;

import java.util.logging.Logger;

import javaeetutorial.dukesbookstore.entity.Customer;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateful
public class CustomerRequestBean {

	@PersistenceContext
	private EntityManager em;
	private static final Logger logger =
			Logger.getLogger("dukesbookstore.ejb.CustomerRequestBean");

	public CustomerRequestBean() throws Exception {

	}

	public void createCustomer(String userId, String password, String name, String surname, String email, String creditCardNumber, String language){
		try{
			if(getCustomerUserId(userId)==null){
				Customer customer = new Customer(userId,password,name,surname,email,creditCardNumber,language);
				em.persist(customer);
			}
			else {
				System.out.println("ERROR! HOLI MARCOS");
			}
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
	}

	private Customer getCustomerUserId(String userId) {
		Customer requestCustomer = em.find(Customer.class, userId);
		if(requestCustomer == null)
			return null;
		else 
			return requestCustomer;

	}
}
