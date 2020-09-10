package com.example.mobizone.model;

/**
 * @author Patel Dhruv
 * @author Gakhar Tanvi
 * @author Kaur Sarbjit
 * @author Kaur Kamaljit
 * @author Varma Akshay
 * This adapter class is used for Products Category.
 */
public class ProductCategory {

    /**
     * int for productId
     */
    Integer productId;
    /**
     * productCategory String
     */
    String productCategory;

    /**
     * Constructor
     * @param productId
     * @param productCategory
     */
    public ProductCategory(Integer productId, String productCategory) {
        this.productId = productId;
        this.productCategory = productCategory;
    }

    /**
     * getter of productId
     * @return
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * setter for productId
     * @param productId
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * getter for productCategory
     * @return
     */
    public String getProductCategory() {
        return productCategory;
    }

    /**
     * setter for productCategory
     * @param productCategory
     */
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
