package com.vm.guides.bestpractui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.vm.guides.bestpractui.fragments.Fragments;
import com.vm.guides.common.FragmentUtil;

public class MaterialActivity extends Activity {

    private static final String TAG = MaterialActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_material, menu);
        int index = 0;
        for (Class<? extends Fragment> fragmentClass : Fragments.FRAGMENT_LIST) {
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

        if (id != android.R.id.home) {
            int index = id - Menu.FIRST;
            Class<? extends Fragment> fragmentClass = Fragments.FRAGMENT_LIST.get(index);
            Log.i(TAG, "add fragment " + fragmentClass.getSimpleName());
            FragmentUtil.addFragment(this, fragmentClass, R.id.fragment, true);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
