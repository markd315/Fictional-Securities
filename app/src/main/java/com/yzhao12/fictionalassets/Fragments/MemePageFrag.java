package com.yzhao12.fictionalassets.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzhao12.fictionalassets.R;

/**
 * Created by Yang on 4/1/2017.
 */

public class MemePageFrag extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.vote_page, container, false);
    }

    public MemePageFrag() {

    }
}
