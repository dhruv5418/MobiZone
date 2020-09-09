package com.example.mobizone.model;

import android.net.Uri;
/**
 * @author Patel Dhruv
 * @author Gakhar Tanvi
 * @author Sarbjit Kaur
 * @author Kamaljit Kaur
 * @author Akshay Varma
 * model class for order
 */
public class Order {

    String productName;
    String productQty;
    String productPrice;
    String apt;
    String postal;
    String city;
    String address;
    String province;
    String  detail_image;


    /**
     * constructor
     * @param productName
     * @param productQty
     * @param productPrice
     * @param apt
     * @param postal
     * @param city
     * @param address
     * @param province
     * @param detail_image
     */
    public Order(String productName, String productQty, String productPrice, String apt, String postal, String city, String address, String province, String detail_image) {
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.apt = apt;
        this.postal = postal;
        this.city = city;
        this.address = address;
        this.province = province;
        this.detail_image = detail_image;
    }

    /**
     * getter method for Product Name
     * @return
     */
    public String getProductName() {
        return productName;
    }

    /**
     * setter method for Product Name
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * getter method for product quantity
     * @return
     */
    public String getProductQty() {
        return productQty;
    }

    /**
     * setter method for product quantity
     * @param productQty
     */
    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    /**
     * getter method for product price
     * @return
     */
    public String getProductPrice() {
        return productPrice;
    }

    /**
     * setter method for Product Price
     * @param productPrice
     */
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * getter method for Apt
     * @return
     */
    public String getApt() {
        return apt;
    }

    /**
     * setter method of Apt
     * @param apt
     */
    public void setApt(String apt) {
        this.apt = apt;
    }

    /**
     * getter method for Postal code
     * @return
     */
    public String getPostal() {
        return postal;
    }

    /**
     *  setter method for Postal code
     * @param postal
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }

    /**
     * getter method for City
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * setter method for City
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * getter method for Address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * setter method for Address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getter method for Province
     * @return
     */
    public String getProvince() {
        return province;
    }

    /**
     * setter method for Province
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * getter method for image detail
     * @return
     */
    public String getDetail_image() {
        return detail_image;
    }

    /**
     * setter method for image detail
     * @param detail_image
     */
    public void setDetail_image(String detail_image) {
        this.detail_image = detail_image;
    }
}
