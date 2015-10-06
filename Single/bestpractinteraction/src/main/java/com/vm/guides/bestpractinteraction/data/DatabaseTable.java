package com.vm.guides.bestpractinteraction.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;

import com.vm.guides.bestpractinteraction.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DatabaseTable {

    private static final String TAG = DatabaseTable.class.getSimpleName();

    public static final String COL_WORD = "WORD";
    public static final String COL_DEFINITION = "DEFINITION";

    private static final String DATABASE_NAME = "DICTIONARY";
    private static final String FTS_VIRTUAL_TABLE = "FTS";
    private static final int DATABASE_VERSION = 2;

    private final DatabaseOpenHelper helper;

    public DatabaseTable(Context context) {

        super();
        helper = new DatabaseOpenHelper(context);
    }

    public Cursor getWordMatches(String query, String[] columns) {

        String selection = COL_WORD + " MATCH ?";
        String[] selectionArgs = new String[]{query + "*"};
        return query(selection, selectionArgs, columns);
    }

    private Cursor query(String selection, String[] selectionArgs, String[] columns) {

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(FTS_VIRTUAL_TABLE);

        Cursor cursor = builder.query(helper.getReadableDatabase(), columns, selection, selectionArgs, null, null, null);
        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        } else {
            Log.d(TAG, "found " + cursor.getCount() + " definitions");
        }
        return cursor;
    }

    class DatabaseOpenHelper extends SQLiteOpenHelper {

        private final Context context;

        private SQLiteDatabase database;

        private static final String FTS_TABLE_CREATE = "CREATE VIRTUAL TABLE " + FTS_VIRTUAL_TABLE +
                " USING fts3 (" +
                BaseColumns._ID + ", " +
                COL_WORD + ", " +
                COL_DEFINITION + ")";


        public DatabaseOpenHelper(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            database = db;
            database.execSQL(FTS_TABLE_CREATE);
            loadDictionary();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy " +
                    "all old data");
            db.execSQL("DROP TABLE IF EXISTS " + FTS_VIRTUAL_TABLE);
            onCreate(db);
        }

        private void loadDictionary() {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        loadWords();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }

        private void loadWords() throws IOException {

            Log.i(TAG, "load words");

            final Resources resources = context.getResources();
            InputStream inputStream = resources.openRawResource(R.raw.definitions);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = TextUtils.split(line, "-");
                    if (parts.length < 2) {
                        continue;
                    }
                    long id = addWord(parts[0].trim(), parts[1].trim());
                    if (id < 0) {
                        Log.e(TAG, "unable to add word: " + parts[0].trim());
                    }
                }
            } finally {
                reader.close();
            }
        }

        public long addWord(String word, String definition) {

            ContentValues values = new ContentValues();
            values.put(BaseColumns._ID, word);
            values.put(COL_WORD, word);
            values.put(COL_DEFINITION, definition);
            return database.insert(FTS_VIRTUAL_TABLE, null, values);
        }
    }
}
