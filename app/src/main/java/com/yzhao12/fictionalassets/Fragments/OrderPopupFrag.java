package com.yzhao12.fictionalassets.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.yzhao12.fictionalassets.R;

/**
 * Created by Yang on 9/20/2017.
 */

public class OrderPopupFrag extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("FIRE MISSLES?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.wtf("zhao:", "YES");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.wtf("zhao:", "No");
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
