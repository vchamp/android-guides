package com.vm.guides.bestpractinteraction;

import android.app.Activity;
import android.os.Bundle;

import com.vm.guides.bestpractinteraction.activities.SearchActivity;
import com.vm.guides.common.activity.GuideActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * http://developer.android.com/training/search/index.html
 */
public class BestPractInteractionActivity extends GuideActivity {

    public static final List<Class<? extends Activity>> TOPIC_ACTIVITY_LIST = new ArrayList<>();

    static {
        TOPIC_ACTIVITY_LIST.add(SearchActivity.class);
    }

    @Override
    protected List<Class<? extends Activity>> getTopicActivities() {
        return TOPIC_ACTIVITY_LIST;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_pract_interaction);
    }
}
