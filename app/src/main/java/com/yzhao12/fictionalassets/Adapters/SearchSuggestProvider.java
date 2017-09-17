package com.yzhao12.fictionalassets.Adapters;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yzhao12.fictionalassets.DataObjects.Meme;

import java.util.ArrayList;

/**
 * Created by Yang on 9/16/2017.
 */

public class SearchSuggestProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        memeNames = FirebaseDatabase.getInstance().getReference().child("Memes");
        memeNames.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot oneMeme : dataSnapshot.getChildren()) {
                    suggestions.add(oneMeme.getValue(Meme.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.wtf("zhao", "SearchSuggestProvider ValueEventListener cancelled");
            }
        });

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String[] columns = {"_ID", "SUGGEST_COLUMN_TEXT_1", "SUGGEST_COLUMN_TEXT_2", "SUGGEST_COLUMN_INTENT_DATA"};
        MatrixCursor result = new MatrixCursor(columns);

        for(Meme suggestion: suggestions) {
            if(suggestion.getTicker().length() >= ) {

            }
        }

        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return "vnd.android.cursor.dir/vnd.com.yzhao12.fictionalassets.Adapters.SearchSuggestProvider.suggestions";
    }


    private DatabaseReference memeNames;
    private ArrayList<Meme> suggestions;
}
