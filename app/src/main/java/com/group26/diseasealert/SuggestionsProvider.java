package com.group26.diseasealert;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.CursorAdapter;

/**
 * Created by newho on 4/28/2016.
 */
public class SuggestionsProvider extends ContentProvider {

    private CursorAdapter mCursorAdapter;

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        if (selectionArgs[0].length() > 0) {//some text present in search box
            String[] columns = {
                    BaseColumns._ID,
                    SearchManager.SUGGEST_COLUMN_TEXT_1,
                    SearchManager.SUGGEST_COLUMN_INTENT_DATA,
                    SearchManager.SUGGEST_COLUMN_INTENT_EXTRA_DATA};

            MatrixCursor cursor = new MatrixCursor(columns);
            Log.e("Suggestions", ""+selectionArgs[0]);
            if(selectionArgs[0].equals("m")){
                String[] temp2 = {"0", "Measles", "Measles", "Measles"};
                String[] temp = {"1", "Mumps", "Mumps", "Mumps"};
                cursor.addRow(temp2);
                cursor.addRow(temp);
            }
            else if(selectionArgs[0].equals("mu")){
                String[] temp = {"0", "Mumps", "Mumps", "Mumps"};
                cursor.addRow(temp);
            }
            else if(selectionArgs[0].equals("me")){
                String[] temp = {"1", "Measles", "Measles", "Measles"};
                cursor.addRow(temp);
            }
            return cursor;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
