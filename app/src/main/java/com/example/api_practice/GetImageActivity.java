package com.example.api_practice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetImageActivity extends AppCompatActivity {
    TextView ratingview,countview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_image);
        ImageView imageView=findViewById(R.id.imageBrandCollections);
         ratingview=findViewById(R.id.id_rating);
         countview=findViewById(R.id.id_count);
        String imageUrl = getIntent().getStringExtra("IMAGE_URL");
        double rating = getIntent().getDoubleExtra("RATING_URL", 0.0);
        int count = getIntent().getIntExtra("COUNT_URL", 0);

        if (imageUrl != null) {
            Log.e("onimagesucc", "onimagesucc: " + imageUrl);

            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView);
        }
        ratingview.setText(String.valueOf(rating));
        countview.setText(String.valueOf(count));
    }
}