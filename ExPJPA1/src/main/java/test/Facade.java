/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Benedikte
 */
public class Facade {

   public void createCustomer(EntityManager em, Customer c){
       c.createCustomer(em, c);
   }
   public Customer findCustomerById(EntityManager em, long id){
    Customer c = entity.Customer.findCustomerById(em, id);
    return c;
   }
    
    

    
    

  
}
