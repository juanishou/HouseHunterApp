package com.example.househunters.Services;


import com.example.househunters.Model.AddPropertyDto;
import com.example.househunters.Model.AddPropertyResponse;
import com.example.househunters.Model.Property;
import com.example.househunters.Model.PropertyFavDto;
import com.example.househunters.Model.ResponseContainer;
import com.example.househunters.Model.ResponseContainerDetail;
import com.example.househunters.Model.addFavourite;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PropertyService {
    @GET("/properties")
    Call<ResponseContainer<Property>> getProperties();

    @GET("/properties/auth")
    Call<ResponseContainer<Property>> getAuthProperties();

    @GET("/properties/{id}")
    Call<Property> getProperty(@Path("id") String id);

    @GET("/properties/fav")
    Call<ResponseContainer<Property>> getFavProperties();

    @POST("/properties/fav/{id}")
    Call<addFavourite> addFavProperty(@Path("id") String id);

    @DELETE("/properties/fav/{id}")
    Call<addFavourite> removeFavProperty(@Path("id") String id);

    @GET("/properties/mine")
    Call<ResponseContainer<PropertyFavDto>> getMineProperties();

    @GET("/properties/{id}")
    Call<ResponseContainerDetail<Property>> getOneProperty(@Path("id") String id);

    @POST("/properties")
    Call<AddPropertyResponse> addProperty(@Body AddPropertyDto addPropertyDto);

    @PUT("/properties/{id}")
    Call<AddPropertyResponse> editProperty(@Path("id") String id, @Body AddPropertyDto addPropertyDto);

    @DELETE("/properties/{id}")
    Call<ResponseContainerDetail<Property>> deleteProperty(@Path("id") String id);
}
