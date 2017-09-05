package com.yzhao12.fictionalassets.Fragments;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yzhao12.fictionalassets.DataObjects.User;
import com.yzhao12.fictionalassets.R;

/**
 * Created by Yang on 3/31/2017.
 */

public class ProfilePageFrag extends Fragment {

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_auth = FirebaseAuth.getInstance();
        user = m_auth.getCurrentUser();
        userData = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.profile_page, container, false);
        profileName = (TextView)view.findViewById(R.id.profile_name);
        profileMoney = (TextView)view.findViewById(R.id.profile_money);


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                profileMoney.setText(dataSnapshot.getValue(User.class).getUserMoney());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("zhao:", "loadPost:onCancelled", databaseError.toException());
            }
        };
        userData.addValueEventListener(postListener);

        profileName.setText(user.getDisplayName());

        return view;
    }

    public ProfilePageFrag() {

    }


    private FirebaseUser user;
    private DatabaseReference userData;
    private FirebaseAuth m_auth;
    private TextView profileName;
    private TextView profileMoney;
    private View view;
}
