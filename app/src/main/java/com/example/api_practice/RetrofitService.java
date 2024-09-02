package com.example.api_practice;

import static com.example.api_practice.MainActivity.apiUrl;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    static  RetrofitService retrofitService;
   RetrofitInterface retrofitInterface;
    RetrofitService(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(apiUrl).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface= retrofit.create(RetrofitInterface.class);
    }
    public static  RetrofitService getUserDetails(){
        if(retrofitService==null){
            retrofitService=new RetrofitService();
        }
        return retrofitService;
    }
}
