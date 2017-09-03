package com.yzhao12.fictionalassets;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Yang on 8/31/2017.
 */


public class LoginActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.login);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        if(auth.getCurrentUser() != null) {
            setResult(RESULT_OK);
            finish();
        } else {
            startActivityForResult(
                    AuthUI.getInstance().createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false).build(),
                    RC_SIGN_IN
            );
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
                startActivityForResult(
                        AuthUI.getInstance().createSignInIntentBuilder().build(),
                        RC_SIGN_IN
                );
            }
        }
    }






    private FirebaseAuth auth;
    private static final int RC_SIGN_IN = 2;
    private FirebaseDatabase database;
}
