package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {
    private List<Product> productList;
    private ProductAdapter adapter;
    private boolean isPriceAscending = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        String category = getIntent().getStringExtra("category");

        // Initialize back button
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize product list
        productList = initializeProducts(category);
        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        // Initialize filter button
        ImageButton filterButton = findViewById(R.id.filter_button);
        filterButton.setOnClickListener(v -> sortProducts());
    }

    private List<Product> initializeProducts(String category) {
        List<Product> products = new ArrayList<>();
        if ("laptops".equals(category)) {
            products.add(new Product("18\" Ноутбук MSI Stealth 18 AI Studio A1VHG черный", 199.99, R.drawable.msistealthh));
            products.add(new Product("13.3\" Ноутбук Apple MacBook Air серый", 1199.99, R.drawable.images));
            products.add(new Product("17.3\" Ноутбук MSI Katana GF76 B12UCR-821XRU черный", 499.99, R.drawable.msistealth));
            // Add more laptops
        } else {
            products.add(new Product("ПК ARDOR GAMING NEO M143", 1299.99, R.drawable.ardor));
            products.add(new Product("ПК DEXP Atlas H444", 999.99, R.drawable.dexp));
            // Add more PCs
        }
        return products;
    }

    private void sortProducts() {
        if (isPriceAscending) {
            productList.sort(Comparator.comparingDouble(Product::getPrice));
        } else {
            productList.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        }
        isPriceAscending = !isPriceAscending;
        adapter.notifyDataSetChanged();
    }
}