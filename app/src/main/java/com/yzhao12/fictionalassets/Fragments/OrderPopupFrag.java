package com.yzhao12.fictionalassets.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yzhao12.fictionalassets.DataObjects.Meme;
import com.yzhao12.fictionalassets.DataObjects.User;
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

        orderType = (RadioGroup)orderPopup.findViewById(R.id.order_buySell);
        money = (TextView)orderPopup.findViewById(R.id.order_money);
        total = (TextView)orderPopup.findViewById(R.id.order_total);
        price = (TextView)orderPopup.findViewById(R.id.order_price);
        shares = (EditText)orderPopup.findViewById(R.id.order_shares);

        return orderPopup;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orders = FirebaseDatabase.getInstance().getReference().child("Orders");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        userInfo = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid());
        userInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);
                money.setText("Your Money: " + currentUser.getUserMoney());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void loadMemeInfo(String ticker) {
        DatabaseReference meme = FirebaseDatabase.getInstance().getReference().child("Memes").child(ticker);
        meme.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                memeInfo = dataSnapshot.getValue(Meme.class);
                price.setText(memeInfo.getPrice());
                if(shares.getText().toString() != null) {
                    sharesOrdered = Integer.getInteger(shares.getText().toString());
                }
                total.setText(Integer.toString(sharesOrdered * Integer.getInteger(memeInfo.getPrice())));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private DatabaseReference orders;
    private DatabaseReference userInfo;
    private Meme memeInfo;
    private RadioGroup orderType;
    private TextView money;
    private TextView total;
    private TextView price;
    private EditText shares;
    private int sharesOrdered = 0;

}
