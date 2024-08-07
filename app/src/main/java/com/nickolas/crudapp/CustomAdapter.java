package com.nickolas.crudapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList product_id, product_code, product_name, product_price;

    int position;

    CustomAdapter(Activity activity, Context context, ArrayList product_id, ArrayList product_code, ArrayList product_name,
                  ArrayList product_price) {
        this.activity = activity;
        this.context = context;
        this.product_id = product_id;
        this.product_code = product_code;
        this.product_name = product_name;
        this.product_price = product_price;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.product_id_txt.setText(String.valueOf(product_id.get(position)));
        holder.product_code_txt.setText(String.valueOf(product_code.get(position)));
        holder.product_name_txt.setText(String.valueOf(product_name.get(position)));
        holder.product_price_txt.setText(String.valueOf(product_price.get(position)));

        holder.mainLayout.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {  // Ensure position is valid
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(product_id.get(currentPosition)));
                intent.putExtra("code", String.valueOf(product_code.get(currentPosition)));
                intent.putExtra("name", String.valueOf(product_name.get(currentPosition)));
                intent.putExtra("price", String.valueOf(product_price.get(currentPosition)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView product_id_txt, product_code_txt, product_name_txt, product_price_txt;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id_txt = itemView.findViewById(R.id.product_id_txt);
            product_code_txt = itemView.findViewById(R.id.product_code_txt);
            product_name_txt = itemView.findViewById(R.id.product_name_txt);
            product_price_txt = itemView.findViewById(R.id.product_price_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
