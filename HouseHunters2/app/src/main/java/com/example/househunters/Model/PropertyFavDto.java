package com.example.househunters.Model;

import java.util.Arrays;

public class PropertyFavDto {

    private String title;
    private String description;
    private double price;
    private int rooms;
    private String address;
    private String zipcode;
    private String city;
    private String province;
    private String loc;
    private String ownerId;
    private String id;
    private String[] photos;

    public PropertyFavDto() {
    }

    public PropertyFavDto(String title, String description, double price, int rooms, String address, String zipcode, String city, String province, String loc, String ownerId, String id, String[] photos) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.rooms = rooms;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
        this.ownerId = ownerId;
        this.id = id;
        this.photos = photos;
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

    public String getOwnerId() {
        return ownerId;
    }

    public String getId() {
        return id;
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

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "PropertyFavDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", loc='" + loc + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", id='" + id + '\'' +
                ", photos=" + Arrays.toString(photos) +
                '}';
    }
}
