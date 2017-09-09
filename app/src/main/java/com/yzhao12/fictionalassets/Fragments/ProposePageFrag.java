package com.yzhao12.fictionalassets.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yzhao12.fictionalassets.DataObjects.Meme;
import com.yzhao12.fictionalassets.R;

/**
 * Created by Yang on 4/1/2017.
 */

public class ProposePageFrag extends Fragment {
    public ProposePageFrag() {

    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memes = FirebaseDatabase.getInstance().getReference().child("Proposed_Memes");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.propose_page, container, false);
        nameView = (EditText)view.findViewById(R.id.propose_name);
        tickerView = (EditText)view.findViewById(R.id.propose_ticker);
        descriptionView = (EditText)view.findViewById(R.id.propose_description);





        return view;
    }

    public void submitMeme(View view) {
        String name = nameView.getText().toString();
        String ticker = tickerView.getText().toString();
        String description = descriptionView.getText().toString();
        Meme proposal = new Meme();
    }

    private DatabaseReference memes;
    private EditText nameView;
    private EditText tickerView;
    private EditText descriptionView;
}
