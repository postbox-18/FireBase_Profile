package com.example.firebase_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    Button logout;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;

    TextView name,email;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        name=findViewById(R.id.prfName);
        email=findViewById(R.id.prfemail);
        img=findViewById(R.id.prfimg);
        logout=findViewById(R.id.logout);

        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        name.setText(currentUser.getEmail());
        email.setText(currentUser.getDisplayName());


        Glide
                .with(this)
                .load(currentUser.getPhotoUrl())
                .into(img);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent=new Intent(HomeActivity.this,Login_Activity.class);
                startActivity(intent);
            }
        });


    }
}