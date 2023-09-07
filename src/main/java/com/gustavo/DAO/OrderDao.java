package com.gustavo.DAO;

import com.gustavo.Model.Order;

import javax.persistence.EntityManager;

public class OrderDao {

    EntityManager em;

    public OrderDao(EntityManager em) {
        this.em = em;
    }

    public void registerOrder(Order order){
        this.em.persist(order);
    }

}
