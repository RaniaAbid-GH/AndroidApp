package com.dev.myappp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<Item> items;
    private Context context;
    private double  budget;
    private List<String> categories;
    private ActivityResultLauncher<Intent> itemDetailActivityResultLauncher;



    public ImageAdapter(Context context, List<Item> items, ActivityResultLauncher<Intent> itemDetailActivityResultLauncher) {
        this.context = context;
        this.items  = items;
        this.itemDetailActivityResultLauncher = itemDetailActivityResultLauncher;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.titleTextView.setText("Title: "+item.getTitle());
        holder.priceTextView.setText("Price: "+String.format(Locale.US, "$%.2f", item.getPrice()));
        Glide.with(context).load(item.getImageResource()).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra("item", item);
            intent.putExtra("budget", budget);// pass the remaining budget
            //context.startActivity(intent);
            // Start ItemDetailActivity and expect a result back
            //((Activity) context).startActivityForResult(intent, 1);
            itemDetailActivityResultLauncher.launch(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView ;
        TextView priceTextView ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
        }
    }

    public void updateBudget(double newBudget) {
        this.budget = newBudget;
    }
}
