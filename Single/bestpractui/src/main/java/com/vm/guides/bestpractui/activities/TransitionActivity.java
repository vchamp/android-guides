package com.vm.guides.bestpractui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.vm.guides.bestpractui.R;

public class TransitionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // must be called before adding content
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_transition);
    }
}
