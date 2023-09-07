package com.gustavo.DAO;

import com.gustavo.Model.Customer;

import javax.persistence.EntityManager;

public class CustomerDao {

    EntityManager em;

    public CustomerDao(EntityManager em) {
        this.em = em;
    }

    public void register(Customer customer){
        this.em.persist(customer);
    }

    public Customer findById(Long id){
        return this.em.find(Customer.class, id);
    }

}
