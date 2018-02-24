/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import enums.CustomerType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Ejer
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    
     
    //denne bruges til at se hvad der sker når vi skifter mapped by i et manyToMany relationsship
    private Address address;

    public Customer() {
    }

    public Customer(String firstName, String lastName, Address address, CustomerType customerType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.customerType = customerType;
    }

   

    // -----------------unidirectional onetomany-------------
//    @OneToMany 
//    @JoinColumn(name="CUSTOMER_ID")
    // private List<Address> addresses = new ArrayList();
   
    // ------------------bidirectional onetomany-------------
//    @OneToMany(mappedBy = "customer")
//    private List<Address> addresses = new ArrayList();
    
    //---------------------bidirectional manytomany-------------------
    // der må ikke stå mappe by begge steder
   
    @ManyToMany(mappedBy = "customers")
    private List<Address> addresses = new ArrayList();
    
    ;


    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @ManyToOne(optional = true)
    @ElementCollection()
    private final List<String> hobbies = new ArrayList();

    public void addHobby(String s) {
        hobbies.add(s);
    }

    public List<String> getHobbies() {
        return hobbies;

    }
    
   
     public void addAddress(Address address){
        addresses.add(address);
    }

    public List<Address> getAddresses() {
        return addresses;

    }
    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name = "PHONE")
    @Column(name = "Description")
    private Map<String, String> phones = new HashMap();

    public void addPhone(String phoneNo, String description) {
        phones.put(phoneNo, description);
    }

    public String getPhoneDescription(String phoneNo) {
        String description = phones.get(phoneNo);

        return description;

    }

//Add a method:  addPhone(String phoneNo, String description){..}
//Add a method:  getPhoneDescription(String phoneNo){..}
//Add a few phone numbers to your customer in the Tester class, and execute (which should regenerate the tables).
    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
     public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Customer[ id=" + id + " ]";
    }

}
