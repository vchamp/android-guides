package com.vm.guides.common.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.vm.guides.common.R;
import com.vm.guides.common.util.FragmentUtil;

import java.util.List;

public abstract class TopicCompatActivity extends AppCompatActivity {

    private static final String TAG = TopicCompatActivity.class.getSimpleName();

    protected abstract List<Class<? extends Fragment>> getTopicFragments();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //        getMenuInflater().inflate(R.menu.menu_material, menu);
        int index = 0;
        for (Class<? extends Fragment> fragmentClass : getTopicFragments()) {
            menu.add(Menu.NONE, Menu.FIRST + index, Menu.NONE, fragmentClass.getSimpleName());
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

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id != android.R.id.home) {
            int index = id - Menu.FIRST;
            Class<? extends Fragment> fragmentClass = getTopicFragments().get(index);
            Log.i(TAG, "show fragment " + fragmentClass.getSimpleName());
            FragmentUtil.addFragment(this, fragmentClass, R.id.fragment, true);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
