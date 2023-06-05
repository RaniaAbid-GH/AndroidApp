package com.dev.myappp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> books;
    private List<Item> audios;
    private List<Item> movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = new Intent(MainActivity.this, MainActivity.class);
        //intializing the budget to spend
        //intent.putExtra("budget", 200.00);
        //startActivity(intent);
        Button cdButton ;
        Button bookButton ;
        Button movieButton ;
        bookButton = findViewById(R.id.bookButton);
        movieButton = findViewById(R.id.movieButton);
        cdButton = findViewById(R.id.cdButton);

        initializeData();

        bookButton.setOnClickListener(v -> startGalleryActivity(books));
        movieButton.setOnClickListener(v -> startGalleryActivity(movies));
        cdButton.setOnClickListener(v -> startGalleryActivity(audios));
        //





    }
    private void startGalleryActivity(List<Item> items) {
        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
        intent.putParcelableArrayListExtra("items", new ArrayList<>(items));
        intent.putExtra("budget", 200.00);
        startActivity(intent);
    }
    private void initializeData() {
        books = new ArrayList<>();
        books.add(new Item(R.drawable.book_1, "The Journey", 12.99, "book"));
        books.add(new Item(R.drawable.book_2, "The Spirit", 8, "book"));
        books.add(new Item(R.drawable.book_3, "The Eyes Of Sarah", 7.45, "book"));
        books.add(new Item(R.drawable.book_4, "Beautiful Dreams", 16.80, "book"));
        books.add(new Item(R.drawable.book_5, "The Lost Kingdom", 12.99, "book"));
        books.add(new Item(R.drawable.book_6, "The Wicked Princess", 17.30, "book"));
        books.add(new Item(R.drawable.book_7, "Sword", 11.99, "book"));
        books.add(new Item(R.drawable.book_8, "Steam", 12.99, "book"));
        books.add(new Item(R.drawable.book_9, "Pencil", 4.6, "book"));
        books.add(new Item(R.drawable.book_10, "Dawn Tomorrow", 5.7, "book"));

        // add more books


        audios = new ArrayList<>();
        audios.add(new Item(R.drawable.audio1, "Morgan Maxwell", 10, "audio"));
        audios.add(new Item(R.drawable.audio2, "Never Stop", 10, "audio"));
        audios.add(new Item(R.drawable.audio3, "Broke", 10, "audio"));
        audios.add(new Item(R.drawable.audio4, "Days With You", 10, "audio"));
        audios.add(new Item(R.drawable.audio5, "Samira Hadid", 10, "audio"));
        audios.add(new Item(R.drawable.audio6, "Chidi Eze", 10, "audio"));
        audios.add(new Item(R.drawable.audio7, "Guilty", 10, "audio"));
        audios.add(new Item(R.drawable.audio8, "Listen Now", 10, "audio"));
        audios.add(new Item(R.drawable.audio9, "Thug Life", 10, "audio"));

        // add more movies

        movies = new ArrayList<>();
        movies.add(new Item(R.drawable.movie_1,  "Warrior", 14.99 ,"movie" ));
        movies.add(new Item(R.drawable.movie_2,  "Monster", 14.99 ,"movie"  ));
        movies.add(new Item(R.drawable.movie_3,  "Dark", 14.99  ,"movie" ));
        movies.add(new Item(R.drawable.movie_4,  "Future Destiny", 14.99 ,"movie"  ));
        movies.add(new Item(R.drawable.movie_5,  "The Rundown", 14.99  ,"movie" ));
        movies.add(new Item(R.drawable.movie_6,  "Hide Me", 14.99  ,"movie" ));
        movies.add(new Item(R.drawable.movie_7,  "Help Me", 14.99  ,"movie" ));

    }

}