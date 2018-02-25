/*

 */
package test;

import entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ejer
 */
public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExPJPA1PU");
        EntityManager em = emf.createEntityManager();
        Facade f = new Facade();

        try {
            em.getTransaction().begin();
            f.createCustomer(em, new Customer("Børge Andersen", "email@mail.com"));
            f.createCustomer(em, new Customer("Winnie Wienerpølse", "pølse@pils.dk"));
            f.createCustomer(em, new Customer("Karl Kedelig", "kedelig@karl.eu"));
            
            em.getTransaction().commit();
System.out.println( f.findCustomerById(em, 1));
        } finally {
            em.close();

        }

    }
}
