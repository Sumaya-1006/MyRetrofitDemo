package com.example.myretrofitdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myretrofitdemo.R;
import com.example.myretrofitdemo.activity.MainActivity;
import com.example.myretrofitdemo.model.ImageModel;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private Context context;
    private ArrayList<ImageModel> list;

    public ImageAdapter(Context context, ArrayList<ImageModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items,parent,false);
        return new ImageViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.date.setText(String.valueOf(list.get(position).getCreated_at()));
        holder.description.setText(String.valueOf(list.get(position).getAlt_description()));
        Glide.with(context).load(list.get(position).getUrls().getRegular()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
class ImageViewHolder extends RecyclerView.ViewHolder {
    TextView date,description;
    ImageView imageView;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);

        date = itemView.findViewById(R.id.dateId);
        description = itemView.findViewById(R.id.desTxtId);
        imageView = itemView.findViewById(R.id.imageId);
    }
}
