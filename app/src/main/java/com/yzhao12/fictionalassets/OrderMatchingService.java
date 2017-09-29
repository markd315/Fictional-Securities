package com.yzhao12.fictionalassets;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

public class OrderMatchingService extends IntentService {

    public OrderMatchingService() {
        super("OrderMatchingService");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }
}
