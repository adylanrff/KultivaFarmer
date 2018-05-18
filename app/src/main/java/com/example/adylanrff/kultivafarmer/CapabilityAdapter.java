package com.example.adylanrff.kultivafarmer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.List;

    public class CapabilityAdapter extends RecyclerView.Adapter<CapabilityAdapter.ViewHolder> {

    private List<Capability> capabilities;
    private RequestManager glide;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView price;
        public TextView capacity;
        public ImageView image;


        public ViewHolder(View v) {
            super(v);

            title =  v.findViewById(R.id.crop_name_tv);
            image = v.findViewById(R.id.crop_iv);
            price = v.findViewById(R.id.crop_price_tv);
            capacity = v.findViewById(R.id.capacity_tv);

        }
    }

    public CapabilityAdapter(RequestManager glide, List<Capability> capabilities) {
        this.glide = glide;
        this.capabilities=capabilities;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Capability capability = capabilities.get(position);
        holder.title.setText(capability.getProductName());
        holder.price.setText(capability.getProductPrice());
        holder.capacity.setText("" + capability.getVolume() + " Kg");
        glide.load(capability.getProductImage())
                .centerCrop()
                .into(holder.image);

    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }

    @Override
    public CapabilityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crops_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return capabilities.size();
    }
}
