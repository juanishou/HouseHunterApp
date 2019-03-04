package com.example.househunters.Services;

import com.example.househunters.Model.Category;
import com.example.househunters.Model.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("/categories")
    Call<ResponseContainer<Category>> getCategories();
}
