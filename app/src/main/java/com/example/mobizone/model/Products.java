package com.example.mobizone.model;

/**
 *  @author Dhruv Patel
 *   @author Gakhar Tanvi
 *  @author Sarbjit Kaur
 *  @author Kamaljit Kaur
 *  @author Akshay Varma
 *  This java class is  model class for products
 */
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

    /**
     * getter method for product id
     * @return
     */
    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    /**
     * getter method for product name
     * @return
     */
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    /**
     * getter method for product price
     * @return
     */
    public String getProductPrice() {
        return productPrice;
    }

    /**
     * setter method for product price
     * @param productPrice
     */
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * getter method for image url
     * @return
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * setter method for image url
     * @param imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * getter method for image detail
     * @return
     */
    public String getImage_detail() {
        return image_detail;
    }

    /**
     * setter method is for image detail
     * @param image_detail
     */
    public void setImage_detail(String image_detail) {
        this.image_detail = image_detail;
    }

    /**
     * getter method for company
     * @return
     */
    public String getCompany() {
        return company;
    }

    /**
     * setter method for company
     * @param company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * getter method for battery
     * @return
     */
    public String getBattery() {
        return battery;
    }

    /**
     * setter method for battery
     * @param battery
     */
    public void setBattery(String battery) {
        this.battery = battery;
    }

    /**
     * getter method for front camera
     * @return
     */
    public String getFrntCam() {
        return frntCam;
    }

    /**
     * setter method for front camera
     * @param frntCam
     */
    public void setFrntCam(String frntCam) {
        this.frntCam = frntCam;
    }

    /**
     * getter method for back camera
     * @return
     */
    public String getBckCam() {
        return bckCam;
    }

    /**
     * setter method for back camera
     * @param bckCam
     */
    public void setBckCam(String bckCam) {
        this.bckCam = bckCam;
    }

    /**
     * getter method for memory
     * @return
     */
    public String getMemory() {
        return memory;
    }

    /**
     * setter method for memory
     * @param memory
     */
    public void setMemory(String memory) {
        this.memory = memory;
    }

    /**
     * get method for processor
     * @return
     */
    public String getProcessor() {
        return processor;
    }

    /**
     * set method for processor
     * @param processor
     */
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
