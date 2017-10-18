package com.yzhao12.fictionalassets;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yzhao12.fictionalassets.DataObjects.Order;
import com.yzhao12.fictionalassets.DataObjects.OrderMeme;
import com.yzhao12.fictionalassets.DataObjects.PortfolioItem;
import com.yzhao12.fictionalassets.DataObjects.User;

import java.util.ArrayList;


public class OrderMatchingService extends Service {
    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            final Intent intent = (Intent)msg.obj;
            final Order order = new Order(intent.getIntExtra("shares", -1), intent.getFloatExtra("price", -1), intent.getStringExtra("userid"));
            String ticker = intent.getStringExtra("ticker");

            final DatabaseReference newTicker = FirebaseDatabase.getInstance().getReference().child("Orders").child(ticker);
            if(!trackedTickers.contains(ticker)) {
                newTicker.addValueEventListener(matcher);
                trackedTickers.add(newTicker);
            }

            newTicker.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    OrderMeme memeOrderBook = dataSnapshot.getValue(OrderMeme.class);
                    if(intent.getIntExtra("type", -1) == R.id.order_buy) {
                        ArrayList<Order> buys = memeOrderBook.getBuy();
                        for(int i = buys.size() - 1; i >= 0; i--) {
                            if(buys.get(i).getPrice() >= intent.getFloatExtra("price", -1)) {
                                if(i == buys.size() - 1) {
                                    buys.add(order);
                                    memeOrderBook.setBuy(buys);
                                    newTicker.setValue(memeOrderBook);
                                    return;
                                } else {
                                    buys.add(i + 1, order);
                                    memeOrderBook.setBuy(buys);
                                    newTicker.setValue(memeOrderBook);
                                    return;
                                }
                            }
                        }
                        buys.add(0, order);
                        memeOrderBook.setBuy(buys);
                        newTicker.setValue(memeOrderBook);
                    } else if(intent.getIntExtra("type", -1) == R.id.order_sell) {
                        ArrayList<Order> sells = memeOrderBook.getSell();
                        for(int i = sells.size() - 1; i >= 0; i--) {
                            if(sells.get(i).getPrice() <= intent.getFloatExtra("price", -1)) {
                                if(i == sells.size() - 1) {
                                    sells.add(order);
                                    memeOrderBook.setSell(sells);
                                    newTicker.setValue(memeOrderBook);
                                    return;
                                } else {
                                    sells.add(i + 1, order);
                                    memeOrderBook.setSell(sells);
                                    newTicker.setValue(memeOrderBook);
                                    return;
                                }
                            }
                        }
                        sells.add(0, order);
                        memeOrderBook.setSell(sells);
                        newTicker.setValue(memeOrderBook);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userProfileRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid());
        userProfileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userProfile = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        trackedTickers = new ArrayList<>();
        Log.wtf("zhao:", "SERVICE ONCREATE()");
        matcher = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.wtf("zhao:", "WHAT THE FUCK THIS SHOULD FIRE");
                OrderMeme thisMeme = dataSnapshot.getValue(OrderMeme.class);
                ArrayList<Order> buys = thisMeme.getBuy();
                ArrayList<Order> sells = thisMeme.getSell();

                int i = 0;
                int j = 0;
                for( ; i < buys.size() && j < sells.size(); i++, j++) {
                    DatabaseReference thisMemeref = FirebaseDatabase.getInstance().getReference().child("Orders").child(thisMeme.getTicker());

                    if((buys.get(i).getUserid().equals(currentUser.getUid()) || sells.get(i).getUserid().equals(currentUser.getUid())) &&
                            buys.get(i).getPrice() >= sells.get(j).getPrice()) {
                        Toast.makeText(OrderMatchingService.this, "MATCHED AN ORDER, SHARES: " + buys.get(i).getShares(), Toast.LENGTH_SHORT).show();

                        ArrayList<PortfolioItem> portfolio = userProfile.getUserPortfolio();
                        if(buys.get(i).getUserid().equals(currentUser.getUid())) {
                            portfolio.add(new PortfolioItem(thisMeme.getTicker(), buys.get(i).getShares(), buys.get(i).getPrice()));
                        } else if(sells.get(i).getUserid().equals(currentUser.getUid())) {
                            for(int x = 0; x < portfolio.size(); x++) {
                                if((portfolio.get(i).getTicker() == thisMeme.getTicker()) &&
                                        (portfolio.get(i).getPrice() == buys.get(i).getPrice()) &&
                                        (portfolio.get(i).getShares() == buys.get(i).getShares())) {

                                }
                            }
                        }

                        buys.remove(i);
                        sells.remove(j);

                        OrderMeme temp = new OrderMeme(thisMeme.getTicker());
                        temp.setBuy(buys);
                        temp.setSell(sells);
                        thisMemeref.setValue(temp);

                        i--;
                        j--;
                    } else {
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        msg.obj = intent;
        mServiceHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }



    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    private FirebaseUser currentUser;
    private ValueEventListener matcher;
    private ArrayList<DatabaseReference> trackedTickers;
    private DatabaseReference userProfileRef;
    private User userProfile;
}