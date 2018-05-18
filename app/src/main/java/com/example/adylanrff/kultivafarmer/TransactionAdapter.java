package com.example.adylanrff.kultivafarmer;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.Date;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {


private List<Transaction> capabilities;
private RequestManager glide;

public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView noTransaction;
    public TextView dateTransaction;


    public ViewHolder(View v) {
        super(v);

        noTransaction =  v.findViewById(R.id.no_transaction_tv);
        dateTransaction = v.findViewById(R.id.date_transaction_tv);

    }
}

    public TransactionAdapter(RequestManager glide, List<Transaction> capabilities) {
        this.glide = glide;
        this.capabilities=capabilities;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(TransactionAdapter.ViewHolder holder, final int position) {
        final Transaction transaction = capabilities.get(position);
        holder.noTransaction.setText("Pesanan " + "#" + (position+1));
        holder.dateTransaction.setText("20-00-00");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), TransactionDetailActivity.class);
                i.putExtra("transaction", transaction);
                i.putExtra("number", (position+1));
                v.getContext().startActivity(i);
            }
        });

    }

    public List<Transaction> getCapabilities() {
        return capabilities;
    }

    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_item,parent,false);

        return new TransactionAdapter.ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return capabilities.size();
    }
}
