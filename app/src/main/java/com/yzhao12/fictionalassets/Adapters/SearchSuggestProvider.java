package com.yzhao12.fictionalassets.Adapters;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yzhao12.fictionalassets.DataObjects.Meme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Yang on 9/16/2017.
 */

public class SearchSuggestProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        memeNames = FirebaseDatabase.getInstance().getReference().child("Memes");
//        memeNames.child("TEST").setValue(new Meme("Test meme", "TEST", "test test", "123.34", null, null));
//        memeNames.child("PEPE").setValue(new Meme("Pepe the frog", "PEPE", "The sad frog in various forms", "111.11", null, null));
//        memeNames.child("PUPPER").setValue(new Meme("Doggos", "PUPPER", "Cute dogs with retarded words", "222.22", null, null));
//        memeNames.child("NANI").setValue(new Meme("The triggered copypasta", "NANI", "Nani the fuck did you just say to me", "333.33", null, null));

        suggestions = new ArrayList<>();
        id = 0;
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
        String[] columns = {BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1, SearchManager.SUGGEST_COLUMN_TEXT_2, SearchManager.SUGGEST_COLUMN_INTENT_DATA};
        MatrixCursor result = new MatrixCursor(columns);

        for(Meme suggestion: suggestions) {
            if(suggestion.getTicker().length() >= selectionArgs[0].length() &&
                    suggestion.getTicker().substring(0, selectionArgs[0].length()).equals(selectionArgs[0])) {
                String[] row = {Integer.toString(id), suggestion.getTicker(), suggestion.getName(), suggestion.getTicker()};
                id++;
                Log.wtf("zhao", Arrays.toString(row));
                result.addRow(row);
            }
        }

        return result;
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
    private int id;
}
