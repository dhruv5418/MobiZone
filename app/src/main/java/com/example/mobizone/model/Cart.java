package com.example.mobizone.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 *  @author Dhruv Patel
 *   @author Gakhar Tanvi
 *  @author Sarbjit Kaur
 *  @author Kamaljit Kaur
 *  @author Akshay Varma
 *  This java class is  model class for cart
 */
public class Cart implements Parcelable{
    /**
     * Integer for productId
     */
    Integer productid;
    /**
     * String for product name
     */
    String productName;
    /**
     * String for product quantity
     */
    String productQty;
    /**
     * String product price
     */
    String productPrice;
    /**
     * String image url
     */
    String imageUrl;


    /**
     * Constructor
     * @param productid
     * @param productName
     * @param productQty
     * @param productPrice
     * @param imageUrl
     */
    public Cart(Integer productid, String productName, String productQty, String productPrice, String imageUrl) {

        this.productid = productid;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.imageUrl = imageUrl;

    }

    /**
     *
     * @param in
     */
    protected Cart(Parcel in) {
        if (in.readByte() == 0) {
            productid = null;
        } else {
            productid = in.readInt();
        }
        productName = in.readString();
        productQty = in.readString();
        productPrice = in.readString();
        imageUrl = in.readString();
    }

    /**
     * parcelable creator method
     */
    public static final Parcelable.Creator<Cart> CREATOR = new Parcelable.Creator<Cart>() {

        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    /**
     * getter method for productname
     * @return
     */
    public String getProductName() {
        return productName;
    }

    /**
     * setter method for product name
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
     * setter method for product Quantity
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
     * setter method for product price
     * @param productPrice
     */
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * getter mehod for image url
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
     * getter method for product id
     * @return
     */
    public Integer getProductid() {
        return productid;
    }

    /**
     * setter method for product id
     * @param productid
     */
    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    /**
     *
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *
     * @param parcel
     * @param i
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (productid == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(productid);
        }
        parcel.writeString(productName);
        parcel.writeString(productQty);
        parcel.writeString(productPrice);
        parcel.writeString(imageUrl);
    }
}
