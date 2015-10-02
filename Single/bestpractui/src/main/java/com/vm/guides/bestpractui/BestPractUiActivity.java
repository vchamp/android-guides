package com.vm.guides.bestpractui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.vm.guides.bestpractui.activities.CustomViewActivity;
import com.vm.guides.bestpractui.activities.MaterialActivity;
import com.vm.guides.bestpractui.activities.MaterialActivityPreAPI21;

import java.util.ArrayList;
import java.util.List;

public class BestPractUiActivity extends Activity {

    private static final String TAG = BestPractUiActivity.class.getSimpleName();

    public static final List<Class<? extends Activity>> TOPIC_ACTIVITY_LIST = new ArrayList<>();

    static {
        TOPIC_ACTIVITY_LIST.add(MaterialActivity.class);
        TOPIC_ACTIVITY_LIST.add(MaterialActivityPreAPI21.class);
        TOPIC_ACTIVITY_LIST.add(CustomViewActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_best_pract_ui);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //        getMenuInflater().inflate(R.menu.menu_main, menu);

        int index = 0;
        for (Class<? extends Activity> activityClass : TOPIC_ACTIVITY_LIST) {
            MenuItem menuItem = menu.add(Menu.NONE, Menu.FIRST + index, Menu.NONE, activityClass.getSimpleName());
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            index++;
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id != android.R.id.home) {
            int index = id - Menu.FIRST;
            Class<? extends Activity> activityClass = TOPIC_ACTIVITY_LIST.get(index);
            Log.i(TAG, "open topic " + activityClass.getSimpleName());
            startActivity(new Intent(this, activityClass));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
