package com.vm.guides;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.vm.guides.bestpractui.MaterialActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.addButton).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_material) {
            startActivity(new Intent(this, MaterialActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.addButton) {
            PopupMenu menu = new PopupMenu(this, v);
            for (Class<?> fragmentClass : Fragments.FRAGMENT_LIST) {
                menu.getMenu().add(fragmentClass.getSimpleName());
            }
            menu.setOnMenuItemClickListener(addFragmentListener);
            menu.show();
        }
    }

    private PopupMenu.OnMenuItemClickListener addFragmentListener = new PopupMenu
            .OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            Class<? extends Fragment> fragmentClass = Fragments.FRAGMENT_LIST.get(item.getOrder());
            Log.i(TAG, "add fragment " + fragmentClass.getSimpleName());

            try {
                Fragment fragment = fragmentClass.newInstance();
                //            To make fragment transactions in your activity (such as add,
                // remove, or
                // replace a fragment), you must use APIs from FragmentTransaction
                //            http://developer.android.com/guide/components/fragments.html
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragment, fragment);
                fragmentTransaction.addToBackStack("Undo");
                fragmentTransaction.commit();

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return false;
        }
    };
}
