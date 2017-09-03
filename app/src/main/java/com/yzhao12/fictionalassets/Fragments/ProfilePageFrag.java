package com.yzhao12.fictionalassets.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.yzhao12.fictionalassets.R;

/**
 * Created by Yang on 3/31/2017.
 */

public class ProfilePageFrag extends Fragment {

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_auth = FirebaseAuth.getInstance();



    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.profile_page, container, false);
        profileName = (TextView)view.findViewById(R.id.profile_name);
        profileMoney = (TextView)view.findViewById(R.id.profile_money);

        profileName.setText(m_auth.getCurrentUser().getEmail());
        profileMoney.setText()
        return view;
    }

    public ProfilePageFrag() {

    }


    private FirebaseAuth m_auth;
    private TextView profileName;
    private TextView profileMoney;
    private View view;
}
