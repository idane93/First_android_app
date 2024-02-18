package com.example.favoriteshowassignment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_card_layout);
        String name = getIntent().getStringExtra("name");
        int imageResId = getIntent().getIntExtra("image", 0);
        String detailedInfo = getIntent().getStringExtra("detailedInfo");

        TextView textViewName = findViewById(R.id.textViewDetailedName);
        TextView textViewInfo = findViewById(R.id.textViewDetailedInfo);
        ImageView imageView = findViewById(R.id.imageViewDetailed);

        textViewName.setText(name);
        textViewInfo.setText(detailedInfo);
        imageView.setImageResource(imageResId);
    }
}


