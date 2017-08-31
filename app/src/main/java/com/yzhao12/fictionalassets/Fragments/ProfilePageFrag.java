package com.yzhao12.fictionalassets.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzhao12.fictionalassets.R;

/**
 * Created by Yang on 3/31/2017.
 */

public class ProfilePageFrag extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_page, container, false);
    }

    public ProfilePageFrag() {

    }

}
