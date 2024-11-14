package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button laptopsButton = findViewById(R.id.laptops_button);
        Button pcsButton = findViewById(R.id.pcs_button);

        laptopsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
            intent.putExtra("category", "laptops");
            startActivity(intent);
        });

        pcsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
            intent.putExtra("category", "pcs");
            startActivity(intent);
        });
    }
}