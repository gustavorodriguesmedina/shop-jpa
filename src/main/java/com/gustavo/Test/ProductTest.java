package com.gustavo.Test;

import com.gustavo.DAO.CategoryDao;
import com.gustavo.DAO.ProductDao;
import com.gustavo.Model.Category;
import com.gustavo.Model.Product;
import com.gustavo.Util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductTest {

    public static void main(String[] args) {
        registerProduct();

        EntityManager em = JPAUtil.getEntityManager();

        ProductDao productDao = new ProductDao(em);

        Product p = productDao.findById(1L);
        System.out.println(p.getDescription());

        List<Product> all = productDao.findAll();
        all.forEach(p2 -> System.out.println(p2.getName()));

        List<Product> allByName = productDao.findAllByName("Iphone XR");
        allByName.forEach(p3 -> System.out.println(p3.getValue()));

        List<Product> allByCategoryName = productDao.findAllByCategoryName("PHONES");
        allByName.forEach(p4 -> System.out.println(p4.getId()));

        BigDecimal productPrice = productDao.findProductValueByName("Iphone XR");
        System.out.println(productPrice);
    }

    private static void registerProduct() {
        Category category = new Category("PHONES");
        Product phone = new Product("Iphone XR", "White", new BigDecimal(200), category);

        EntityManager em = JPAUtil.getEntityManager();

        ProductDao productDao = new ProductDao(em);
        CategoryDao categoryDao = new CategoryDao(em);

        em.getTransaction().begin();

        categoryDao.register(category);
        productDao.register(phone);

        em.getTransaction().commit();

        em.close();
    }

}
