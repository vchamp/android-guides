package com.vm.guides.bestpractsecurity;

import android.app.Activity;
import android.os.Bundle;

import com.vm.guides.common.activity.GuideActivity;

import java.util.ArrayList;
import java.util.List;

public class BestPractSecurityActivity extends GuideActivity {

    private static final String TAG = BestPractSecurityActivity.class.getSimpleName();

    public static final List<Class<? extends Activity>> TOPIC_ACTIVITY_LIST = new ArrayList<>();

    static {
        //        TOPIC_ACTIVITY_LIST.add(MaterialActivity.class);
    }

    @Override
    protected List<Class<? extends Activity>> getTopicActivities() {

        return TOPIC_ACTIVITY_LIST;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_pract_security);
    }
}
