package com.example.tha_app_194094e;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Activity activity;
    Context context;
    private final ArrayList id,name,price,description;

    CustomAdapter(Activity activity, Context context,ArrayList id,ArrayList name, ArrayList description, ArrayList price){

        this.activity=activity;
        this.context=context;
        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.description_txt.setText(String.valueOf(description.get(position)));
        holder.price_txt.setText(String.valueOf(price.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,AddActivity.class);

                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("name",String.valueOf(name.get(position)));
                intent.putExtra("description",String.valueOf(description.get(position)));
                intent.putExtra("price",String.valueOf(price.get(position)));
                activity.startActivityForResult(intent,1);


            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_txt,description_txt,price_txt,id_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name_txt=itemView.findViewById(R.id.name_txt);
            description_txt=itemView.findViewById(R.id.description_txt);
            price_txt=itemView.findViewById(R.id.price_txt);
            id_txt=itemView.findViewById(R.id.textView);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
