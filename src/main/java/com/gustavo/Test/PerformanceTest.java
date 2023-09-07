package com.gustavo.Test;

import com.gustavo.DAO.CategoryDao;
import com.gustavo.DAO.CustomerDao;
import com.gustavo.DAO.OrderDao;
import com.gustavo.DAO.ProductDao;
import com.gustavo.Model.*;
import com.gustavo.Util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformanceTest {

    public static void main(String[] args) {
        registerProduct();
        EntityManager em = JPAUtil.getEntityManager();
        OrderDao orderDao = new OrderDao(em);

        Order order = orderDao.findOrderWithCostumer(1L);
        em.close();
        System.out.println(order.getCustomer().getName());
    }

    private static void registerProduct() {
        Category phones = new Category("PHONES");
        Category videogames = new Category("VIDEOGAMES  ");
        Category computing = new Category("COMPUTING");
        Product phone = new Product("Iphone XR", "White", new BigDecimal(200), phones);
        Product videogame = new Product("PS5", "White", new BigDecimal(300), videogames);
        Product macbook = new Product("MacBook", "Gray", new BigDecimal(500), computing);
        Customer customer = new Customer("Gustavo", "010203");
        Order order = new Order(customer);
        order.addItems(new OrderItem(20, videogame, order));

        EntityManager em = JPAUtil.getEntityManager();

        ProductDao productDao = new ProductDao(em);
        CategoryDao categoryDao = new CategoryDao(em);
        CustomerDao customerDao = new CustomerDao(em);
        OrderDao orderDao = new OrderDao(em);

        em.getTransaction().begin();

        categoryDao.register(phones);
        categoryDao.register(videogames);
        categoryDao.register(computing);
        productDao.register(phone);
        productDao.register(videogame);
        productDao.register(macbook);
        customerDao.register(customer);
        orderDao.register(order);

        em.getTransaction().commit();

        em.close();
    }
}
