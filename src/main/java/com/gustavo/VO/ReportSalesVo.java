package com.gustavo.VO;

import java.time.LocalDate;

public class ReportSalesVo {

    private String productName;
    private Long salesQuantity;
    private LocalDate lastSaleDate;

    public ReportSalesVo(String productName, Long salesQuantity, LocalDate lastSaleDate) {
        this.productName = productName;
        this.salesQuantity = salesQuantity;
        this.lastSaleDate = lastSaleDate;
    }

    public String getProductName() {
        return productName;
    }

    public Long getSalesQuantity() {
        return salesQuantity;
    }

    public LocalDate getLastSaleDate() {
        return lastSaleDate;
    }

    @Override
    public String toString() {
        return "ReportSalesVo{" +
                "productName='" + productName + '\'' +
                ", salesQuantity=" + salesQuantity +
                ", lastSaleDate=" + lastSaleDate +
                '}';
    }
}
