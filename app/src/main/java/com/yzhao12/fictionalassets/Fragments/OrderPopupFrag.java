package com.yzhao12.fictionalassets.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
import com.yzhao12.fictionalassets.DataObjects.Order;
import com.yzhao12.fictionalassets.DataObjects.OrderMeme;
import com.yzhao12.fictionalassets.DataObjects.User;
import com.yzhao12.fictionalassets.R;

import java.util.ArrayList;


/**
 * Created by Yang on 9/20/2017.
 */

public class OrderPopupFrag extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.order_dialog, null);

        builder.setView(view)
                .setNeutralButton("Place Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "ORDER PLACED", Toast.LENGTH_LONG).show();




//                        meme = FirebaseDatabase.getInstance().getReference().child("Memes").child(ticker);
//                        meme.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                memeInfo = dataSnapshot.getValue(Meme.class);
//                                final Order order = new Order(sharesOrdered, Float.parseFloat(memeInfo.getPrice()), FirebaseAuth.getInstance().getCurrentUser().getUid());
//                                orders.child(ticker).addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(DataSnapshot dataSnapshot) {
//                                        OrderMeme orderMeme = dataSnapshot.getValue(OrderMeme.class);
//                                        if(orderType.getCheckedRadioButtonId() == R.id.order_buy) {
//                                            //ArrayList<Order> test = new ArrayList<Order>();
//                                            ArrayList<Order> test = orderMeme.getBuy();
//                                            test.add(order);
//                                            orderMeme.setBuy(test);
//                                        } else if(orderType.getCheckedRadioButtonId() == R.id.order_sell) {
//                                            orderMeme.getSell().add(order);
//                                        }
//                                        orders.child(ticker).setValue(orderMeme);
//                                    }
//
//                                    @Override
//                                    public void onCancelled(DatabaseError databaseError) {
//
//                                    }
//                                });
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
                    }
                });

        AlertDialog orderPopup = builder.create();

        orderType = (RadioGroup)view.findViewById(R.id.order_buySell);
        money = (TextView)view.findViewById(R.id.order_money);
        total = (TextView)view.findViewById(R.id.order_total);
        price = (TextView)view.findViewById(R.id.order_price);
        shares = (EditText)view.findViewById(R.id.order_shares);

        return orderPopup;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orders = FirebaseDatabase.getInstance().getReference().child("Orders");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        userInfo = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid());

    }

    public void loadMemeInfo(String ticker) {
        this.ticker = ticker;
        meme = FirebaseDatabase.getInstance().getReference().child("Memes").child(ticker);
        meme.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                memeInfo = dataSnapshot.getValue(Meme.class);

                price.setText("Price: " + memeInfo.getPrice());

                shares.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(!shares.getText().toString().isEmpty()) {
                            sharesOrdered = Integer.parseInt(s.toString());
                            total.setText("Total: " + Double.toString(Integer.parseInt(s.toString()) * Double.parseDouble(memeInfo.getPrice())));
                        } else {
                            sharesOrdered = 0;
                            total.setText("Total: 0");
                        }
                    }
                });


                Log.wtf("zhao: memeinfo", memeInfo.getTicker());
                total.setText("Total: " + Double.toString(sharesOrdered * Double.parseDouble(memeInfo.getPrice())));



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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.wtf("zhao: ORDER", "LOADMEMEINFO");

    }



    private DatabaseReference orders;
    private DatabaseReference userInfo;
    private DatabaseReference meme;
    private Meme memeInfo;
    private RadioGroup orderType;
    private String ticker;


    private TextView money;
    private TextView total;
    private TextView price;
    private EditText shares;
    private int sharesOrdered = 0;

}
