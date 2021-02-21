package com.example.busyatri;



import androidx.annotation.NonNull;

import com.example.busyatri.Common.Common;
import com.example.busyatri.Model.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,MainFragment.onFragmentBtnSelected{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    String UserId="";
    DatabaseReference user;
    TextView nUsername;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
         nUsername = findViewById(R.id.username);
        View headerView = navigationView.getHeaderView(0);
        nUsername=headerView.findViewById(R.id.username);
        nUsername.setText(Common.currentUser.getName());




        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //load default fragment
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment,new MainFragment());
        fragmentTransaction.commit();











    }


    public void logout(View view) {
       /* FirebaseAuth.getInstance().signOut();*/
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        if(menuItem.getItemId() == R.id.home){

            Intent intent = new Intent(MainActivity.this,BusList.class);
            startActivity(intent);
            return false;



        }
        if(menuItem.getItemId() == R.id.book){

        }

        if(menuItem.getItemId() == R.id.logout){
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();

        }

        return true;
    }

    @Override
    public void onButtonSelected() {
        Intent intent = new Intent(MainActivity.this,BusList.class);
        startActivity(intent);


    }
   /* public void setUsername(){

        DatabaseReference table_user = FirebaseDatabase.getInstance().getReference("Users");
        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Fetch values from you database child and set it to the specific view object.
                if(dataSnapshot.exists()) {
                    String name = dataSnapshot.child("Name").getValue(User.class).toString();
                    nUsername.setText(name);
                }

            }

            //SIMPLE BRO. HAVE FUN IN ANDROID <3 GOOD LUCK

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
   public void setUsername(){

       user.child(UserId).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               User user = dataSnapshot.getValue(User.class);
               nUsername.setText(user.getName());
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
   }
}
