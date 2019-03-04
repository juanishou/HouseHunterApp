package com.example.househunters.Model;

import java.util.Arrays;

public class addFavourite {

    private String role;
    private String[] favs;
    private String[] keywords;
    private String _id;
    private String picture;
    private String name;
    private String email;
    private String password;

    public addFavourite() {
    }

    public addFavourite(String role, String[] favs, String[] keywords, String _id, String picture, String name, String email, String password) {
        this.role = role;
        this.favs = favs;
        this.keywords = keywords;
        this._id = _id;
        this.picture = picture;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String[] getFavs() {
        return favs;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public String get_id() {
        return _id;
    }

    public String getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFavs(String[] favs) {
        this.favs = favs;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "addFavourite{" +
                "role='" + role + '\'' +
                ", favs=" + Arrays.toString(favs) +
                ", keywords=" + Arrays.toString(keywords) +
                ", _id='" + _id + '\'' +
                ", picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
