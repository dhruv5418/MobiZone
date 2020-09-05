package com.example.mobizone.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable{
    Integer productid;
    String productName;
    String productQty;
    String productPrice;
    String imageUrl;


    public Cart(Integer productid, String productName, String productQty, String productPrice, String imageUrl) {

        this.productid = productid;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.imageUrl = imageUrl;

    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

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
