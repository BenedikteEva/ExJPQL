/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ejer
 */
   
   public class CustomerFacade {
  EntityManagerFactory emf;

  public CustomerFacade(EntityManagerFactory emf) {
    this.emf = emf;
  }
  EntityManager getEntityManager(){ 
     return emf.createEntityManager();
  }

//Structure of each method that uses the EntityManager  → Use the pattern given below:

  
  
  
  public Customer getCustomer(int id){
EntityManager em = getEntityManager();
try{

   
 
}finally{
  em.close();
}
      return null;
  }
public List<Customer> getCustomers(){
      return null;
    
}


//Customer addCustomer(Customer cust);
//Customer deleteCustomer(int id);
//Customer editCustomer(Customer cust);


}
