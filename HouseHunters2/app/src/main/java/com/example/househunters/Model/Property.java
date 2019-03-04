package com.example.househunters.Model;

import java.util.Arrays;

public class Property {

    public String title;
    public String description;
    public Integer price;
    public Integer rooms;
    public String address;
    public String zipcode;
    public String city;
    public String province;
    public String loc;
    public OwnerIdAdd ownerId;
    public String createdAt;
    public String updatedAt;
    public Integer v;
    public String id;
    public String[] photos = null;
    public boolean isFav;

    public Property() {
    }

    public Property(String title, String description, Integer price, Integer rooms, String address, String zipcode, String city, String province, String loc, OwnerIdAdd ownerId, String createdAt, String updatedAt, Integer v, String id, String[] photos, boolean isFav) {
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.v = v;
        this.id = id;
        this.photos = photos;
        this.isFav = isFav;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getRooms() {
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

    public OwnerIdAdd getOwnerId() {
        return ownerId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public String getId() {
        return id;
    }

    public String[] getPhotos() {
        return photos;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setRooms(Integer rooms) {
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

    public void setOwnerId(OwnerIdAdd ownerId) {
        this.ownerId = ownerId;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    @Override
    public String toString() {
        return "Property{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", loc='" + loc + '\'' +
                ", ownerId=" + ownerId +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", v=" + v +
                ", id='" + id + '\'' +
                ", photos=" + Arrays.toString(photos) +
                ", isFav=" + isFav +
                '}';
    }
}



class OwnerId {
    private String id;
    private String picture;
    private String name;

    public OwnerId(String id, String picture, String name) {
        this.id = id;
        this.picture = picture;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
