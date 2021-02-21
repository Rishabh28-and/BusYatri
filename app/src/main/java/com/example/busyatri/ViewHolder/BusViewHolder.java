package com.example.busyatri.ViewHolder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.busyatri.Interface.ItemClickListener;
import com.example.busyatri.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView bus_name;
    public ImageView bus_image;
    public TextView dep_dest;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public BusViewHolder(@NonNull View itemView) {
        super(itemView);
        bus_name = (TextView)itemView.findViewById(R.id.bus_name);
        bus_image = (ImageView) itemView.findViewById(R.id.bus_image);
        dep_dest=(TextView)itemView.findViewById(R.id.dep_dest);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onclick(view,getAdapterPosition(),false);

    }
}
