package com.example.mobizone.model;

public class ProductCategory {

    Integer productId;
    String productCategory;

    public ProductCategory(Integer productId, String productCategory) {
        this.productId = productId;
        this.productCategory = productCategory;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
