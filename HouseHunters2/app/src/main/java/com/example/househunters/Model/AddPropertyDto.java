package com.example.househunters.Model;

import java.util.Arrays;
import java.util.Objects;

public class AddPropertyDto {

    private String title;
    private String description;
    private double price;
    private int rooms;
    private String categoryId;
    private String address;
    private String zipcode;
    private String city;
    private String province;
    private String loc;
    private String[] photos;

    public AddPropertyDto(String title, String description, double price, int rooms, String address, String zipcode, String city, String province, String loc, String[] photos) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.rooms = rooms;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
        this.photos = photos;
    }

    public AddPropertyDto(String title, String description, double price, int rooms, String categoryId, String address, String zipcode, String city, String province, String loc) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.rooms = rooms;
        this.categoryId = categoryId;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
    }

    public AddPropertyDto(String title, String description, double price, int rooms, String address, String zipcode, String city, String province, String loc) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.rooms = rooms;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getRooms() {
        return rooms;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getLoc() {
        return loc;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }


}
