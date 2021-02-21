package com.example.busyatri;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;


import java.util.Calendar;

public class AddDetail extends AppCompatActivity {
    private static final String[] places = new String[]{"Kathmandu", "Pokhara", "Janakpur", "Dhangadi", "Biratnagar"};
    TextView dep;
    TextView dest;
    TextView bname;
    TextView bnmb;
    EditText mTraveldate;
    EditText mContact;
    Button mAddDetail;
    TextView price;
    DatePickerDialog.OnDateSetListener setListener;
    ProgressDialog dialog;
    FirebaseDatabase database;
    DatabaseReference bus;
    String busId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_detail_add);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.7),(int)(height*.68));
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        dialog = new ProgressDialog(this);
        mContact = findViewById(R.id.contact);
        mAddDetail = findViewById(R.id.adddetail);
        mTraveldate = findViewById(R.id.dot);
        dep = findViewById(R.id.dep);
        dest = findViewById(R.id.dest);
        bname = findViewById(R.id.bname);
        bnmb = findViewById(R.id.bnmb);
        price = findViewById(R.id.price);

        dep.setVisibility(View.INVISIBLE);
        dest.setVisibility(View.INVISIBLE);
        bname.setVisibility(View.INVISIBLE);
        bnmb.setVisibility(View.INVISIBLE);
        price.setVisibility(View.INVISIBLE);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Intent intent = getIntent();
        String str1 = intent.getStringExtra("Dp");
        String str2 = intent.getStringExtra("Dt");
        String str3 = intent.getStringExtra("prc");
        String str4 = intent.getStringExtra("bname");
        String str5 = intent.getStringExtra("bnumb");
        dep.setText(str1);
        dest.setText(str2);
       price.setText(str3);
        bname.setText(str4);
        bnmb.setText(str5);


        mTraveldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddDetail.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                mTraveldate.setText(date);
            }
        };


        mAddDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact = mContact.getText().toString();
                String date = mTraveldate.getText().toString();
                String dept = dep.getText().toString();
                String des = dest.getText().toString();
                String prc = price.getText().toString();
                String bna=  bname.getText().toString();
                String bnb= bnmb.getText().toString();
                Intent intent = new Intent(AddDetail.this, BookingDetail.class);

                intent.putExtra("contact",contact);
                intent.putExtra("date",date);
                intent.putExtra("dept",dept);
                intent.putExtra("des",des);
                intent.putExtra("prc",prc);
                intent.putExtra("bu_na",bna);
                intent.putExtra("bu_nu",bnb);
                startActivity(intent);
            }
        });



            }

    }


