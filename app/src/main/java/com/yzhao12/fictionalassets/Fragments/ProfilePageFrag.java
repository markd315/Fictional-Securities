package com.yzhao12.fictionalassets.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yzhao12.fictionalassets.Adapters.PortfolioAdapter;
import com.yzhao12.fictionalassets.DataObjects.PortfolioItem;
import com.yzhao12.fictionalassets.DataObjects.User;
import com.yzhao12.fictionalassets.R;

import java.util.ArrayList;

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
        portfolioList = (ListView)view.findViewById(R.id.portfolio);



        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(User.class) != null) {
                    profileMoney.setText(Double.toString(dataSnapshot.getValue(User.class).getUserMoney()));
                    Log.wtf("zhao:", Double.toString(dataSnapshot.getValue(User.class).getUserMoney()));
                    ArrayList<PortfolioItem> portfolio = dataSnapshot.getValue(User.class).getUserPortfolio();
                    portfolioAdapter = new PortfolioAdapter(getActivity(), R.layout.profile_portfolio_item, portfolio);
                    if (portfolio != null) {
                        portfolioList.setAdapter(portfolioAdapter);
                    }
                }
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


    private PortfolioAdapter portfolioAdapter;

    private TextView profileName;
    private TextView profileMoney;
    private ListView portfolioList;
    private View view;
}
