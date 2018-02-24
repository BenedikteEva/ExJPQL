/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Address;
import entity.Book;
import entity.Customer;
import enums.CustomerType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ejer
 */
public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            testCustomers(em);
            testBooks(em);
            testCustomersAndAdress(em);

            Customer c = em.find(Customer.class, (long) 3);
            Address a = em.find(Address.class, (long) 5);
            Book b = em.find(Book.class, (long) 4);
            System.out.println("Customername for customer_id 5: " + c.getFirstName());
            System.out.println("Hobbies = " + c.getHobbies());
            System.out.println("Phones=  " + c.getPhoneDescription("55555555"));
            System.out.println("Customer for address_id 5 " + a.getCustomer().getFirstName());
            System.out.println("Booktitle for book_id 4: " + b.getTitle());
            

        } finally {
            em.close();
        }
    }

    public static void testCustomers(EntityManager em) {

        // 1. test om vi kan finde en customer i databasen med et specifikt id som blev sat ind fra data.sql
        Customer c = em.find(Customer.class, (long) 1);

        // 2. test set a c1 customer in db with hobbies and customertype
        Customer c1 = new Customer();
        c1.setFirstName("Willy");
        c1.setLastName("Wæmmelig");
        c1.addHobby("gogo-dans");
        c1.addHobby("Knipling");
        c1.addHobby("Middelalderlige torturmetoder");
        c1.setCustomerType(CustomerType.RUSTY);
        // 3. test set  customer in db with some hobbies with hobbies and customertype
        Customer c2 = new Customer();
        c2.setFirstName("Jørgen");
        c2.setLastName("Jampelev");
        c2.addHobby("Formel 1");
        c2.addHobby("Fodbold");
        c2.addHobby("Madlavning");
        c2.setCustomerType(CustomerType.IRON);
        //test add phone uden annotation er det en grim blob i customer tabellen
        c1.addPhone("55555555", "Gammel og grim Nokia");
        // gør kunderne klar til at blive lagt i db
        em.persist(c);
        em.persist(c1);
        em.persist(c2);
        // udfør skrivningen til databasen
        em.getTransaction().commit();
        //vise at oplysningerne er der når vi kører testmetoden det er mest c der er relevant
//        System.out.println("Customer ID = " + c.getId());
//        System.out.println("Firstname = " + c.getFirstName());
//        System.out.println("CustomerType = " + c.getCustomerType());
//
//        System.out.println("Customer ID = " + c1.getId());
//        System.out.println("Firstname = " + c1.getFirstName());
//        System.out.println("CustomerType = " + c1.getCustomerType());
//        System.out.println("Hobbies = " + c1.getHobbies());
//
//        System.out.println("Customer ID = " + c2.getId());
//        System.out.println("Firstname = " + c2.getFirstName());
//        System.out.println("CustomerType = " + c2.getCustomerType());
//        System.out.println("Hobbies = " + c2.getHobbies());
//        System.out.println("Phones=  " + c2.getPhoneDescription("55555555"));

    }

    public static void testCustomersAndAdress(EntityManager em) {
        em.getTransaction().begin();
        Customer c1 = new Customer();
        c1.setFirstName("Anton");
        c1.setLastName("Alvorligsen");
        c1.addHobby("Fantasy");
        c1.addHobby("Musik");
        c1.setCustomerType(CustomerType.SILVER);

        Customer c2 = new Customer();
        c2.setFirstName("Jørgen");
        c2.setLastName("Jampelev");
        c2.addHobby("Formel 1");
        c2.addHobby("Fodbold");
        c2.addHobby("Madlavning");
        c2.setCustomerType(CustomerType.IRON);
        
        
        

        Address a1 = new Address();
        Address a2 = new Address();
        Address a3 = new Address();
        a1.setStreet("Stilhedens Allé 3");
        a1.setCity("Skoven");
        a1.setCustomer(c1);

        a2.setStreet("Ovenlydsvej 446");
        a2.setCity("Lufthaven");
        a2.setCustomer(c1);

        a3.setStreet("Heltvedsidenafmigselvvej 15");
        a3.setCity("Udenfor");
        a3.setCustomer(c2);
Customer c3 = new Customer("Georg", "Gearløs",a1,CustomerType.GOLD);
        em.persist(c1);
        em.persist(c2);
 em.persist(c3);
        Address a = em.find(Address.class, (long) 2);
        em.persist(a);
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.getTransaction().commit();
        System.out.println("Address med id 3:  " + a.getStreet());

        em.getTransaction().begin();
        Address aFindNewlyPersisted = em.find(Address.class, (long) 5);
        em.persist(aFindNewlyPersisted);
        em.getTransaction().commit();
        System.out.println("Address  id 5 :  " + aFindNewlyPersisted.getStreet() + "  "
                + aFindNewlyPersisted.getCity() + " " + aFindNewlyPersisted.getCustomer().getFirstName());

        // 3. test set a c2 customer in db
    }

    public static void testBooks(EntityManager em) {
        em.getTransaction().begin();

// 1. test om vi kan finde en customer i databasen med et spcifikt id som blev sat ind fra data.sql
        Book b = em.find(Book.class, (long) 1);

        // 2. test set a b1 book in db
        Book b1 = new Book();
        b1.setTitle("Know your insides out of nowhere");
        // 3. test set a c2 customer in db prøver at sætte et ID
        Book b2 = new Book();
        b2.setId(Long.MIN_VALUE);
        b2.setTitle("Checking to see what happens here");
        em.persist(b);
        em.persist(b1);
        em.persist(b2);

        em.getTransaction().commit();
//        System.out.println("Book ID = " + b.getId());
//        System.out.println("Firstname = " + b.getTitle());
//
//        System.out.println("Book ID = " + b1.getId());
//        System.out.println("Firstname = " + b1.getTitle());
//        System.out.println("Book ID = " + b2.getId());
//        System.out.println("Firstname = " + b2.getTitle());
    }

}
