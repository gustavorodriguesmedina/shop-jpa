package com.gustavo.DAO;

import com.gustavo.Model.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductDao {

    private EntityManager em;

    public ProductDao(EntityManager em) {
        this.em = em;
    }

    public void register(Product product){
        this.em.persist(product);
    }

    public Product findById(Long id){
        return em.find(Product.class, id);
    }

    public List<Product> findAll(){
        String jpql = "SELECT p FROM Product p";
        return em.createQuery(jpql, Product.class).getResultList();
    }

    public List<Product> findAllByName(String name){
        String jpql = "SELECT p FROM Product p WHERE p.name = :name";
        return em.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> findAllByCategoryName(String name){
        String jpql = "SELECT p FROM Product p WHERE p.category.name = :name";
        return em.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public BigDecimal findProductValueByName(String name){
        String jpql = "SELECT p.value FROM Product p WHERE p.name = :name";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("name", name)
                .getSingleResult();
    }


}
