package com.example.api_practice;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("/products")
    Call<List<RetrofitEntity>> getUsers();
}
