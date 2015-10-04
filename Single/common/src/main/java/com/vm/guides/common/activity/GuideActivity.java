package com.vm.guides.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public abstract class GuideActivity extends Activity {

    private static final String TAG = GuideActivity.class.getSimpleName();

    protected abstract List<Class<? extends Activity>> getTopicActivities();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //        getMenuInflater().inflate(R.menu.menu_material, menu);
        int index = 0;
        for (Class<? extends Activity> activityClass : getTopicActivities()) {
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
            Class<? extends Activity> activityClass = getTopicActivities().get(index);
            Log.i(TAG, "open topic " + activityClass.getSimpleName());
            startActivity(new Intent(this, activityClass));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
