package com.dev.myappp;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private int imageResource;
    private String title;
    private double price ;
    private String category ;

    public Item(int imageResource, String title, double price, String category) {
        this.imageResource = imageResource;
        this.title = title;
        this.price = price;
        this.category = category ;
    }

    protected Item(Parcel in) {
        imageResource = in.readInt();
        title = in.readString();
        price = in.readDouble();
        category = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    // getters and setters
    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {this.category = category; }

    public String getCategory(){return  category;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageResource);
        parcel.writeString(title);
        parcel.writeDouble(price);
        parcel.writeString(category);
    }

}
