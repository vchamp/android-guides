package com.vm.guides.bestpractinteraction.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.vm.guides.bestpractinteraction.R;
import com.vm.guides.bestpractinteraction.data.DatabaseTable;

public class SearchResultActivity extends Activity {

    private DatabaseTable db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        db = new DatabaseTable(this);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            ((TextView) findViewById(R.id.searchStringTextView)).setText(query);
            Cursor cursor = db.getWordMatches(query, null);
            if (cursor != null) {
                ListView listView = (ListView) findViewById(R.id.resultListView);
                listView.setAdapter(new SimpleCursorAdapter(this, R.layout.list_item_word_definition, cursor, new
                        String[] { DatabaseTable.COL_WORD, DatabaseTable.COL_DEFINITION }, new int[]{R.id.wordTextView,
                        R.id.definitionTextView}, 0));
            }
        }
    }
}
