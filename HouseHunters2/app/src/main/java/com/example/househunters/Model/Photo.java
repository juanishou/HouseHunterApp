package com.example.househunters.Model;

public class Photo {

    public String id;
    public String propertyId;
    public String imgurLink;
    public String deletehash;

    public Photo() {
    }

    public Photo(String id, String propertyId, String imgurLink, String deletehash) {
        this.id = id;
        this.propertyId = propertyId;
        this.imgurLink = imgurLink;
        this.deletehash = deletehash;
    }

    public String getId() {
        return id;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public String getImgurLink() {
        return imgurLink;
    }

    public String getDeletehash() {
        return deletehash;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public void setImgurLink(String imgurLink) {
        this.imgurLink = imgurLink;
    }

    public void setDeletehash(String deletehash) {
        this.deletehash = deletehash;
    }

}
