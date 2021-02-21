package com.example.busyatri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.busyatri.Common.Common;
import com.example.busyatri.Model.Booking;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingDetail extends AppCompatActivity {

    TextView bnumb;
    TextView bname;
    TextView dn;
    TextView dp;
    TextView cn;
    TextView total;
    TextView td;
    Button bookBus;
    FirebaseDatabase database;
    DatabaseReference booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);
        database = FirebaseDatabase.getInstance();
        booking = database.getReference("Booking");

        bname = findViewById(R.id.bname);
        bnumb = findViewById(R.id.bnumb);
        dp= findViewById(R.id.dp);
        dn= findViewById(R.id.dn);
        total= findViewById(R.id.total);
        cn = findViewById(R.id.cn);
        td = findViewById(R.id.td);
        bookBus = findViewById(R.id.book);


        Intent intent = getIntent();
        String str1 = intent.getStringExtra("dept");
        String str2 = intent.getStringExtra("des");
        String str3 = intent.getStringExtra("prc");
        String str4 = intent.getStringExtra("bu_na");
        String str5 = intent.getStringExtra("bu_nu");
        String str6 = intent.getStringExtra("date");
        String str7 = intent.getStringExtra("contact");
        dp.setText(str1);
        dn.setText(str2);
        total.setText(str3);
        bname.setText(str4);
        bnumb.setText(str5);
        td.setText(str6);
        cn.setText(str7);


        bookBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Booking book = new Booking();
                book.setName(Common.currentUser.getName());
                book.setBusName(bname.getText().toString());
                book.setBusNumber(bnumb.getText().toString());
                book.setDeparture(dp.getText().toString());
                book.setDestination(dn.getText().toString());
                book.setPrice(total.getText().toString());
                book.setContactNumber(cn.getText().toString());
                book.setTravelDate(td.getText().toString());

                booking.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(book);
                Toast.makeText(BookingDetail.this, "Booking is successfully done, Thankyou.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
