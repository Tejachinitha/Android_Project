package com.example.api_practice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public  static String apiUrl="https://fakestoreapi.com";
    List<RetrofitEntity> retrofitEntities=new ArrayList<>();
    List<RetrofitEntity> searchListEntities=new ArrayList<>();
    AdapterClass adapterClass;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        searchView=findViewById(R.id.searview_mainActivity);
        RecyclerView recyclerView=findViewById(R.id.recyclerview_mainactivity);
        adapterClass =new AdapterClass(this,retrofitEntities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterClass);
        RetrofitService.getUserDetails().retrofitInterface.getUsers().enqueue(new Callback<List<RetrofitEntity>>() {
            @Override
            public void onResponse(Call<List<RetrofitEntity>> call, Response<List<RetrofitEntity>> response) {

                if (response.body() != null) {
                    retrofitEntities.addAll(response.body());
                    searchListEntities.addAll(response.body());
                    adapterClass.notifyDataSetChanged();
                } else {
                    Log.e("onResponse", "Response body is null");
                }
            }
            @Override
            public void onFailure(Call<List<RetrofitEntity>> call, Throwable t) {
                Log.e("onFailure","onFailure",t);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }
        });
    }
    public  void filter(String text){
        List<RetrofitEntity> filterlist=new ArrayList<>();
        for (RetrofitEntity item : searchListEntities) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                    item.getDescription().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(item);
            }
        }
        adapterClass.filerList(filterlist);
    }
}