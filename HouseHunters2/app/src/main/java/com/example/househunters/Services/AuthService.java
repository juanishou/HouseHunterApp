package com.example.househunters.Services;


import com.example.househunters.Model.LoginResponse;
import com.example.househunters.Model.PassDto;
import com.example.househunters.Model.User;
import com.example.househunters.Model.UserDto;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface AuthService {

    @POST("/auth")
    Call<LoginResponse> doLogin(@Header("Authorization") String authorization);

    @PUT("/users/{id}/password")
    Call<User> updatePass(@Header("Authorization") String authorization, @Path("id") String id, @Body PassDto pass);

    @POST("/users")
    Call<LoginResponse> doRegister(@Body UserDto user);
}
