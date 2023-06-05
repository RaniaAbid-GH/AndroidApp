package com.dev.myappp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Locale;

public class ItemDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleTextView;
    private TextView priceTextView;
    private TextView budgetTextView;
    private Button buyButton;
    private Button playButton;
    private MediaPlayer mediaPlayer;
    private int audioResourceId;

    private double budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity_detail);

        imageView = findViewById(R.id.imageView);
        titleTextView = findViewById(R.id.titleTextView);
        priceTextView = findViewById(R.id.priceTextView);
        playButton = findViewById(R.id.playButton);

        budgetTextView = findViewById(R.id.budgetTextView);
        buyButton = findViewById(R.id.buyButton);
        EditText quantityEditText = findViewById(R.id.quantityEditText);
        quantityEditText.setText("1"); // Set default quantity to 1

        Item item = getIntent().getParcelableExtra("item");
        String category = item.getCategory();
        audioResourceId = R.raw.bird;

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        budget = sharedPreferences.getFloat("budget", 200f);

        if (item != null) {
            Glide.with(this).load(item.getImageResource()).into(imageView);
            titleTextView.setText("Title: " + item.getTitle());
            priceTextView.setText("Price: $" + String.valueOf(item.getPrice()));
        }
        budgetTextView.setText(String.format(Locale.getDefault(), "Budget: $%.2f", budget));

        if (category.equals("audio")) {
            playButton.setVisibility(View.VISIBLE);
        } else {
            playButton.setVisibility(View.GONE);
        }


        // Implement the buy button
        buyButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(quantityEditText.getText().toString());
            double unitPrice = item.getPrice();
            double totalCost = quantity * unitPrice;
            double newBudget = budget - totalCost;
            if (newBudget >= 0) {
                Toast.makeText(this, "You bought the item!", Toast.LENGTH_SHORT).show();
                budgetTextView.setText(String.format("$%.2f", newBudget));

                // Save the updated budget value to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat("budget", (float) newBudget);
                editor.apply();

                // Return the remaining budget to GalleryActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("budget", newBudget);
                setResult(RESULT_OK, resultIntent);
            } else {
                Toast.makeText(this, "You don't have enough money to buy this item!", Toast.LENGTH_SHORT).show();
            }
        });

        // Implement the play button
        playButton.setOnClickListener(v -> {
            if (audioResourceId != 0) {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this, audioResourceId);
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    } else {
                        Toast.makeText(this, "Failed to play audio", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                    } else {
                        mediaPlayer.start();
                    }
                }
            } else {
                Toast.makeText(this, "Audio file not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
