package com.example.adylanrff.kultivafarmer;

import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.List;

public class CropsAdapter extends RecyclerView.Adapter<CropsAdapter.ViewHolder> {

    private List<Crop> crops;
    private RequestManager glide;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;


        public ViewHolder(View v) {
            super(v);

            title =  v.findViewById(R.id.crop_name_tv);
            image = v.findViewById(R.id.crop_iv);
        }
    }

    public CropsAdapter(RequestManager glide, List<Crop> crops) {
        this.glide = glide;
        this.crops=crops;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(CropsAdapter.ViewHolder holder, int position) {
        Crop crop = crops.get(position);
        holder.title.setText(crop.getName());

    }

    public List<Crop> getCrops() {
        return crops;
    }

    @Override
    public CropsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crops_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return crops.size();
    }
}
