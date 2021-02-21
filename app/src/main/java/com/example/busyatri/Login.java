package com.example.busyatri;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.busyatri.Common.Common;
import com.example.busyatri.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class Login extends AppCompatActivity {

    private EditText mPhone, mPassword;
    private Button mRegisterBtn, mLoginBtn;
    RadioButton rg;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPhone = findViewById(R.id.phone);
        mPassword = findViewById(R.id.Password);
        mLoginBtn=findViewById(R.id.LoginBtn);
        mRegisterBtn=findViewById(R.id.btn_signup);
        dialog = new ProgressDialog(this);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Users");

       mLoginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dialog.setMessage("Logging in. please wait..");
               dialog.show();
               table_user.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if(dataSnapshot.child(mPhone.getText().toString()).exists()) {

                           dialog.dismiss();
                           User user = dataSnapshot.child(mPhone.getText().toString()).getValue(User.class);
                           if (user.getPassword().equals(mPassword.getText().toString())) {
                               startActivity(new Intent (Login.this,MainActivity.class));
                               Common.currentUser = user;
                             /*  MainActivity main = new MainActivity();
                               main.setUsername();*/

                               finish();
                           } else {
                               Toast.makeText(Login.this, "Logged in failed !", Toast.LENGTH_SHORT).show();
                           }
                       }else{
                           Toast.makeText(Login.this, "User not exist!!", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
           }
       });
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (getApplicationContext(),Register.class));
            }
        });

    }
}
