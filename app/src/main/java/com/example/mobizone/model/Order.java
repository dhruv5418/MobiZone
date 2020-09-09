package com.example.mobizone.model;

import android.net.Uri;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDetail_image() {
        return detail_image;
    }

    public void setDetail_image(String detail_image) {
        this.detail_image = detail_image;
    }
}
