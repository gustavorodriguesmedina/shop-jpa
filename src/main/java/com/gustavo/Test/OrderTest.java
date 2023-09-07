package com.gustavo.Test;

import com.gustavo.DAO.CategoryDao;
import com.gustavo.DAO.CustomerDao;
import com.gustavo.DAO.OrderDao;
import com.gustavo.DAO.ProductDao;
import com.gustavo.Model.*;
import com.gustavo.Util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class OrderTest {

    public static void main(String[] args) {
        registerProduct();

        EntityManager em = JPAUtil.getEntityManager();

        ProductDao productDao = new ProductDao(em);
        Product product = productDao.findById(1L);
        CustomerDao customerDao = new CustomerDao(em);
        Customer customer = customerDao.findById(1L);

        em.getTransaction().begin();

        Order order = new Order(customer);
        order.addItems(new OrderItem(10, product, order));
        OrderDao orderDao = new OrderDao(em);
        orderDao.registerOrder(order);

        em.getTransaction().commit();
        em.close();
    }

    private static void registerProduct() {
        Category category = new Category("PHONES");
        Product phone = new Product("Iphone XR", "White", new BigDecimal(200), category);
        Customer customer = new Customer("Gustavo", "010203");

        EntityManager em = JPAUtil.getEntityManager();

        ProductDao productDao = new ProductDao(em);
        CategoryDao categoryDao = new CategoryDao(em);
        CustomerDao customerDao = new CustomerDao(em);

        em.getTransaction().begin();

        categoryDao.register(category);
        productDao.register(phone);
        customerDao.register(customer);

        em.getTransaction().commit();

        em.close();
    }

}
