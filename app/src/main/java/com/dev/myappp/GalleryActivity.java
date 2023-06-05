package com.dev.myappp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GalleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Item> items;
    private TextView budgetTextView;
    private double budget;
    private ActivityResultLauncher<Intent> itemDetailActivityResultLauncher;
    private static final int REQUEST_CODE_ITEM_DETAIL = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity);
        //  Get a reference to the TextView
        budgetTextView = findViewById(R.id.budgetTextView);
        budget = 200.0;
        updateBudgetTextView();
        recyclerView = findViewById(R.id.recyclerView);
        items = getIntent().getParcelableArrayListExtra("items");

        itemDetailActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            // Update budget with the one returned from ItemDetailActivity
                            budget = data.getDoubleExtra("budget", 0.0);
                            budgetTextView.setText(String.format("$%.2f", budget));
                        }
                    }
                }
        );

        ImageAdapter myAdapter = new ImageAdapter(this, items, itemDetailActivityResultLauncher);
        recyclerView.setAdapter(myAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter.updateBudget(budget);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ITEM_DETAIL && resultCode == RESULT_OK && data != null) {
            budget = data.getDoubleExtra("budget", 200.0);
            updateBudgetTextView();
        }
    }
    private void updateBudgetTextView() {
        budgetTextView.setText(String.format(Locale.getDefault(), "Budget: $%.2f", budget));

        // Save the updated budget value to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("budget", (float) budget);
        editor.apply();
    }
}

