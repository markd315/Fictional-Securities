package com.yzhao12.fictionalassets.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.yzhao12.fictionalassets.HomepageActivity;
import com.yzhao12.fictionalassets.MainActivity;
import com.yzhao12.fictionalassets.MemeActivity;
import com.yzhao12.fictionalassets.R;

/**
 * Created by Yang on 9/20/2017.
 */

public class OrderPopupFrag extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.order_dialog, null))
                .setNeutralButton("Place Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "ORDER PLACED", Toast.LENGTH_LONG).show();
                    }
                });

        AlertDialog orderPopup = builder.create();
        return orderPopup;
    }
}
