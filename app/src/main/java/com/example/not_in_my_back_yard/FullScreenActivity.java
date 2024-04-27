package com.example.not_in_my_back_yard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullScreenActivity extends AppCompatActivity {

    private ImageView image;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        image = findViewById(R.id.FullScreen_ImageView);
        url = getIntent().getStringExtra("url");

        Glide.with(FullScreenActivity.this)
                .load(url)
                .into(image);
    }
}