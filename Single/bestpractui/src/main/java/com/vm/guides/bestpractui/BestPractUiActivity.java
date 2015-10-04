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
import com.vm.guides.common.activity.GuideActivity;

import java.util.ArrayList;
import java.util.List;

public class BestPractUiActivity extends GuideActivity {

    private static final String TAG = BestPractUiActivity.class.getSimpleName();

    public static final List<Class<? extends Activity>> TOPIC_ACTIVITY_LIST = new ArrayList<>();

    static {
        TOPIC_ACTIVITY_LIST.add(MaterialActivity.class);
        TOPIC_ACTIVITY_LIST.add(MaterialActivityPreAPI21.class);
        TOPIC_ACTIVITY_LIST.add(CustomViewActivity.class);
    }

    @Override
    protected List<Class<? extends Activity>> getTopicActivities() {
        return TOPIC_ACTIVITY_LIST;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_best_pract_ui);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
