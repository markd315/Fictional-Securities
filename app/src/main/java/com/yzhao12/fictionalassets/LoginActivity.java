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

/**
 * Created by Yang on 8/31/2017.
 */


public class LoginActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initVariables();

        if(auth.getCurrentUser() != null) {

        } else {
            startActivityForResult(
                    AuthUI.getInstance().createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false).build(),
                    RC_SIGN_IN
            );
        }
    }

    private void initVariables() {
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                Toast.makeText(this, "Signed in loginac!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Sign in failed loginac", Toast.LENGTH_SHORT).show();
                startActivityForResult(
                        AuthUI.getInstance().createSignInIntentBuilder().build(),
                        RC_SIGN_IN
                );
            }
        }
    }






    private FirebaseAuth auth;
    private static final int RC_SIGN_IN = 2;

}
