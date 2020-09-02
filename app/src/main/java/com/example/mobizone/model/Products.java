package com.example.mobizone.model;

public class Products {

    Integer productid;
    String productName;
    String productPrice;
    String imageUrl;
    String image_detail;
    String company;
    String battery;
    String frntCam;
    String bckCam;
    String memory;
    String processor;
    String os;

    public Products(Integer productid, String productName,  String productPrice, String imageUrl, String image_detail, String company, String battery, String frntCam, String bckCam, String memory, String processor, String os) {
        this.productid = productid;
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageUrl = imageUrl;
        this.image_detail = image_detail;
        this.company = company;
        this.battery = battery;
        this.frntCam = frntCam;
        this.bckCam = bckCam;
        this.memory = memory;
        this.processor = processor;
        this.os = os;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage_detail() {
        return image_detail;
    }

    public void setImage_detail(String image_detail) {
        this.image_detail = image_detail;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getFrntCam() {
        return frntCam;
    }

    public void setFrntCam(String frntCam) {
        this.frntCam = frntCam;
    }

    public String getBckCam() {
        return bckCam;
    }

    public void setBckCam(String bckCam) {
        this.bckCam = bckCam;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
