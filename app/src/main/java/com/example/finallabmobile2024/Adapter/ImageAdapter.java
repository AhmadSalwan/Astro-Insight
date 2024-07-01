package com.example.finallabmobile2024.Adapter;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finallabmobile2024.Models.Images;
import com.example.finallabmobile2024.R;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private ArrayList<Images> imageList;
    private Context context;

    public ImageAdapter(ArrayList<Images> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recyclerview,parent,false);
        return new ImageAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.title.setText(imageList.get(position).getTitle());
        holder.desc.setText(imageList.get(position).getDescription());
        Uri imageUri=Uri.parse(imageList.get(position).getImage());
        Glide.with(context)
                .load(imageUri)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        CardView layout;
        TextView title,desc;
        ImageView image;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            layout=itemView.findViewById(R.id.RecyclerViewLayout);
            title=itemView.findViewById(R.id.tv_rv_title);
            desc=itemView.findViewById(R.id.tv_rv_desc);
            image=itemView.findViewById(R.id.tv_rv_image);
        }
    }
}
