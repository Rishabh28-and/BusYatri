package com.example.busyatri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.busyatri.Interface.ItemClickListener;
import com.example.busyatri.Model.Bus;
import com.example.busyatri.ViewHolder.BusViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class BusList extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference busList;
    FirebaseRecyclerAdapter<Bus, BusViewHolder> adapter;
    FloatingActionButton floatingActionButton;
    private static final int STORAGE_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_bus);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        database = FirebaseDatabase.getInstance();
        busList = database.getReference("Bus");
        recyclerView.setLayoutManager(layoutManager);
        loadListBus();
        floatingActionButton = findViewById(R.id.chat);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        STORAGE_PERMISSION_CODE);





            }
        });


    }
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(BusList.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(BusList.this,
                    new String[] { permission },
                    requestCode);

        }
        else {
            Toast.makeText(BusList.this,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
            startActivity(new Intent(BusList.this,ChatScreen.class));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


         if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(BusList.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
                startActivity(new Intent(BusList.this,ChatScreen.class));
            }
            else {
                Toast.makeText(BusList.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
    private void loadListBus(){
        adapter = new FirebaseRecyclerAdapter<Bus, BusViewHolder>(Bus.class,R.layout.bus_item,BusViewHolder.class,busList) {
            @Override
            protected void populateViewHolder(BusViewHolder busViewHolder, Bus model, int i) {
                busViewHolder.bus_name.setText(model.getBusName());
                busViewHolder.dep_dest.setText(model.getDeparture()+"-"+model.getDestination());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(busViewHolder.bus_image);
                final Bus local = model;
                busViewHolder. setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onclick(View view, int position, boolean isLongClick) {
                       //start activity
                        Intent intent = new Intent(BusList.this, BusDetail.class);
                        intent.putExtra("BusId",adapter.getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
    }

}
