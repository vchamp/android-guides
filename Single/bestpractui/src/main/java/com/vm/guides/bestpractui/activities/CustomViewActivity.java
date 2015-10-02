package com.vm.guides.bestpractui.activities;

import android.app.Fragment;
import android.os.Bundle;

import com.vm.guides.bestpractui.R;
import com.vm.guides.bestpractui.fragments.CustomViewClassFragment;
import com.vm.guides.common.activity.TopicActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomViewActivity extends TopicActivity {

    private static final List<Class<? extends Fragment>> FRAGMENT_LIST = new ArrayList<Class<? extends Fragment>>();

    static {
        FRAGMENT_LIST.add(CustomViewClassFragment.class);
    }

    @Override
    protected List<Class<? extends Fragment>> getTopicFragments() {

        return FRAGMENT_LIST;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }
}
