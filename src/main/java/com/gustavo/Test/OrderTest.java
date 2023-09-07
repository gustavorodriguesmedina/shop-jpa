package com.gustavo.Test;

import com.gustavo.DAO.CategoryDao;
import com.gustavo.DAO.CustomerDao;
import com.gustavo.DAO.OrderDao;
import com.gustavo.DAO.ProductDao;
import com.gustavo.Model.*;
import com.gustavo.Util.JPAUtil;
import com.gustavo.VO.ReportSalesVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class OrderTest {

    public static void main(String[] args) {
        registerProduct();

        EntityManager em = JPAUtil.getEntityManager();

        ProductDao productDao = new ProductDao(em);
        Product phone = productDao.findById(1L);
        Product videogame = productDao.findById(2L);
        Product macbook = productDao.findById(3L);
        CustomerDao customerDao = new CustomerDao(em);
        Customer customer = customerDao.findById(1L);

        em.getTransaction().begin();

        Order order1 = new Order(customer);
        order1.addItems(new OrderItem(10, phone, order1));
        order1.addItems(new OrderItem(20, videogame, order1));

        Order order2 = new Order(customer);
        order2.addItems(new OrderItem(5, macbook, order2));

        OrderDao orderDao = new OrderDao(em);
        orderDao.registerOrder(order1);
        orderDao.registerOrder(order2);

        em.getTransaction().commit();

        BigDecimal totalSold = orderDao.valueTotalSold();
        System.out.println(totalSold);

        List<ReportSalesVo> report = orderDao.reportSales();
        report.forEach(System.out::println);

        em.close();
    }

    private static void registerProduct() {
        Category phones = new Category("PHONES");
        Category videogames = new Category("VIDEOGAMES  ");
        Category computing = new Category("COMPUTING");
        Product phone = new Product("Iphone XR", "White", new BigDecimal(200), phones);
        Product videogame = new Product("PS5", "White", new BigDecimal(300), videogames);
        Product macbook = new Product("MacBook", "Gray", new BigDecimal(500), computing);
        Customer customer = new Customer("Gustavo", "010203");

        EntityManager em = JPAUtil.getEntityManager();

        ProductDao productDao = new ProductDao(em);
        CategoryDao categoryDao = new CategoryDao(em);
        CustomerDao customerDao = new CustomerDao(em);

        em.getTransaction().begin();

        categoryDao.register(phones);
        categoryDao.register(videogames);
        categoryDao.register(computing);
        productDao.register(phone);
        productDao.register(videogame);
        productDao.register(macbook);
        customerDao.register(customer);

        em.getTransaction().commit();

        em.close();
    }

}
