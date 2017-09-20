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
import android.widget.ImageButton;
import android.widget.TextView;

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

        ticker = (TextView)findViewById(R.id.meme_ticker);
        name = (TextView)findViewById(R.id.meme_name);
        price = (TextView)findViewById(R.id.meme_price);
        buy = (ImageButton) findViewById(R.id.meme_buy);
        sell = (ImageButton) findViewById(R.id.meme_sell);


        Intent fromSuggestions = getIntent();
        //Log.wtf("zhao: MemeActivity getting the ticker ", fromSuggestions.getStringExtra("ticker"));
        currentMemeRef = FirebaseDatabase.getInstance().getReference().child("Memes").child(fromSuggestions.getStringExtra("ticker"));

        currentMemeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.wtf("zhao: hit", "HIT");
                currentMeme = dataSnapshot.getValue(Meme.class);

                ticker.setText(currentMeme.getTicker());
                name.setText(currentMeme.getName());
                Log.wtf("zhao: PRICE ", Boolean.toString(currentMeme == null));
                price.setText(currentMeme.getPrice());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.wtf("zhao:", "MemeActivity's currentMemeRef listener cancelled");
            }
        });




    }


    DatabaseReference currentMemeRef;
    Meme currentMeme;
    TextView ticker;
    TextView name;
    TextView price;
    ImageButton buy;
    ImageButton sell;


}
