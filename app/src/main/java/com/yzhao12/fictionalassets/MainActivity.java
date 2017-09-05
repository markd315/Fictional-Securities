package com.yzhao12.fictionalassets;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yzhao12.fictionalassets.DataObjects.PortfolioItem;
import com.yzhao12.fictionalassets.DataObjects.User;
import com.yzhao12.fictionalassets.Fragments.MemePageFrag;
import com.yzhao12.fictionalassets.Fragments.OpeningPageFrag;
import com.yzhao12.fictionalassets.Fragments.ProfilePageFrag;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        m_auth = FirebaseAuth.getInstance();
        m_database = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    public void login(View view) {
        FirebaseUser currentUser = m_auth.getCurrentUser();
        if(currentUser != null) {
            startActivity(new Intent(this, HomepageActivity.class));
        } else {
            Log.wtf("zhao:", "STARTING AUTHUI");

            Intent login = new Intent(this, LoginActivity.class);
            startActivityForResult(login, RC_LOGIN_ACTIVITY);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_LOGIN_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
                Log.wtf("zhao:", "AFTER AUTHUI");
                Log.wtf("zhao:", m_auth.getCurrentUser().getUid());

                ValueEventListener userInfoListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.wtf("zhao:", dataSnapshot.toString());
                        if(!dataSnapshot.exists()) {
                            ArrayList<PortfolioItem> emptyPortfolio = new ArrayList<PortfolioItem>();
                            User newUser = new User(null, 5000, emptyPortfolio);
                            m_database.child(m_auth.getCurrentUser().getUid()).setValue(newUser);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting user info failed, log a message
                        Log.w("zhao:", "loadPost:onCancelled", databaseError.toException());
                    }
                };
                m_database.child(m_auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(userInfoListener);

                startActivity(new Intent(this, HomepageActivity.class));
            } else {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
                Intent login = new Intent(this, LoginActivity.class);
                startActivityForResult(login, RC_LOGIN_ACTIVITY);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private FirebaseAuth m_auth;
    private DatabaseReference m_database;
    public static final int RC_LOGIN_ACTIVITY = 1;
}
