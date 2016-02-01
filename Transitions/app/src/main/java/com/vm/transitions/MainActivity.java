package com.vm.transitions;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeImageTransform;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View label = findViewById(R.id.label);

                Fade fadeOut = new Fade(Fade.OUT);
                fadeOut.addTarget(label);
                TransitionSet exit = new TransitionSet();
                exit.addTransition(fadeOut);
                exit.setDuration(4000);
                exit.addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {

                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, new Pair<>(findViewById(R.id.img1), "img1"));
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                        startActivity(new Intent(MainActivity.this, Activity2.class), options.toBundle());
                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {

                    }

                    @Override
                    public void onTransitionPause(Transition transition) {

                    }

                    @Override
                    public void onTransitionResume(Transition transition) {

                    }
                });

//                getWindow().setExitTransition(exit);
//                TransitionManager transitionManager = new TransitionManager();
//                transitionManager.go(new Scene((ViewGroup) getWindow().getDecorView()), exit);
                TransitionManager.beginDelayedTransition((ViewGroup) getWindow().getDecorView(), exit);
                label.setVisibility(View.INVISIBLE);

//                Fade fadeIn = new Fade(Fade.IN);
//                fadeIn.addTarget(label);
//                TransitionSet reenter = new TransitionSet();
//                reenter.addTransition(fadeIn);
//                reenter.setDuration(2000);
//
//                getWindow().setReenterTransition(reenter);

//                getWindow().setSharedElementsUseOverlay(false);

//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, new Pair<>(findViewById(R.id.img1), "img1"));
////                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
//                startActivity(new Intent(MainActivity.this, Activity2.class), options.toBundle());
            }
        });
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        Log.d("TEMP", "REENTER");
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
        }

        return super.onOptionsItemSelected(item);
    }
}
