package com.gustavo.DAO;

import com.gustavo.Model.Order;
import com.gustavo.VO.ReportSalesVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class OrderDao {

    EntityManager em;

    public OrderDao(EntityManager em) {
        this.em = em;
    }

    public void registerOrder(Order order){
        this.em.persist(order);
    }

    public BigDecimal valueTotalSold(){
        String jpql = "SELECT SUM(o.totalValue) FROM Order o";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    public List<ReportSalesVo> reportSales(){
        String jpql = "SELECT new com.gustavo.VO.ReportSalesVo(product.name, " +
                "SUM(item.quantity), " +
                "MAX(o.date)) " +
                "FROM Order o " +
                "JOIN o.items item " +
                "JOIN item.product product " +
                "GROUP BY product.name " +
                "ORDER BY item.quantity DESC";
        return em.createQuery(jpql, ReportSalesVo.class).getResultList();
    }
}
