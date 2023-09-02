package com.gustavo.DAO;


import com.gustavo.Model.Category;

import javax.persistence.EntityManager;

public class CategoryDao {

    EntityManager em;

    public CategoryDao(EntityManager em){
        this.em = em;
    }

    public void register(Category category){
        this.em.persist(category);
    }

    public void update(Category category){
        this.em.merge(category);
    }

    public void delete(Category category){
        category = em.merge(category);
        this.em.remove(category);
    }

}
