package com.example.busyatri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.busyatri.Common.Common;
import com.example.busyatri.Model.Booking;
import com.example.busyatri.Model.Bus;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class BusDetail extends AppCompatActivity {

    TextView bus_name,bus_price,bus_destination,bus_departure,bus_Contact,busnumber;
    ImageView bus_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    String busId="";
    FirebaseDatabase database;
    DatabaseReference bus;
    ImageView seat_status;
    String seatn="";
    Button booktrip;


    TextView snum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_detail);

        database = FirebaseDatabase.getInstance();
        bus = database.getReference("Bus");
        bus_name = findViewById(R.id.bus_name);
        bus_price = findViewById(R.id.bus_price);
        busnumber = findViewById(R.id.busnumber);
        bus_Contact = findViewById(R.id.bus_Contact);
        bus_departure = findViewById(R.id.bus_Departure);
        bus_destination = findViewById(R.id.bus_Destination);
        bus_image = findViewById(R.id.img_bus);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        booktrip = findViewById(R.id.booktrip);







        if(getIntent() !=null)
            busId = getIntent().getStringExtra("BusId");
        if(!busId.isEmpty())
        {
            getDetailBus(busId);
        }






    }


    private void getDetailBus(String busId) {
        bus.child(busId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Bus bus = dataSnapshot.getValue(Bus.class);

                Picasso.with(getBaseContext()).load(bus.getImage()).into(bus_image);
                collapsingToolbarLayout.setTitle(bus.getBusName());
                bus_price.setText("" +bus.getPrice());
                bus_Contact.setText("" +bus.getContact());
                bus_name.setText(bus.getBusName());
                busnumber.setText( bus.getBusNumber());
                bus_departure.setText(bus.getDeparture());
                bus_destination.setText(bus.getDestination());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        booktrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dep = bus_departure.getText().toString();
                String dest = bus_destination.getText().toString();
                String prc = bus_price.getText().toString();
                String bname= bus_name.getText().toString();
                String bnumb = busnumber.getText().toString();
                Intent intent = new Intent(BusDetail.this, AddDetail.class);

                intent.putExtra("Dp",dep);
                intent.putExtra("Dt",dest);
                intent.putExtra("prc",prc);
                intent.putExtra("bname",bname);
                intent.putExtra("bnumb",bnumb);

                startActivity(intent);

            }
        });
    }
}
