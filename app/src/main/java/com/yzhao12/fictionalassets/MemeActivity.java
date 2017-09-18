package com.yzhao12.fictionalassets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yzhao12.fictionalassets.DataObjects.Meme;

public class MemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent fromSuggestions = getIntent();
        currentMemeRef = FirebaseDatabase.getInstance().getReference().child("Memes").child(fromSuggestions.getStringExtra("ticker"));

        currentMemeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentMeme = dataSnapshot.getValue(Meme.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.wtf("zhao:", "MemeActivity's currentMemeRef listener cancelled");
            }
        });

    }


    DatabaseReference currentMemeRef;
    Meme currentMeme;


}
