package com.yzhao12.fictionalassets;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.yzhao12.fictionalassets.DataObjects.Order;
import com.yzhao12.fictionalassets.DataObjects.OrderMeme;

import java.util.ArrayList;


public class OrderMatchingService extends Service {
    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
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
        trackedTickers = new ArrayList<>();
        orderBookChecker = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Order> buys = dataSnapshot.getValue(OrderMeme.class).getBuy();
                ArrayList<Order> sells = dataSnapshot.getValue(OrderMeme.class).getSell();

                int i = 0;
                int j = 0;
                for( ; i < buys.size() && j < sells.size(); i++, j++) {
                    if((buys.get(i).getUserid() == currentUser.getUid() || sells.get(i).getUserid() == currentUser.getUid()) &&
                            buys.get(i) == sells.get(j)) {

                        Toast.makeText(OrderMatchingService.this, "MATCHED AN ORDER, SHARES: " + buys.get(i).getShares(), Toast.LENGTH_SHORT).show();
                        buys.remove(i);
                        sells.remove(j);
                        i--;
                        j--;
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
    private ArrayList<String> trackedTickers;
    private ValueEventListener orderBookChecker;
}