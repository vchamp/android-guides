package com.vm.guides.bestpractui.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Window;

import com.vm.guides.bestpractui.R;
import com.vm.guides.bestpractui.fragments.CardViewFragment;
import com.vm.guides.bestpractui.fragments.CustomAnimationsFragment;
import com.vm.guides.bestpractui.fragments.DrawablesFragment;
import com.vm.guides.bestpractui.fragments.RecyclerViewFragment;
import com.vm.guides.bestpractui.fragments.ShadowsFragment;
import com.vm.guides.common.activity.TopicActivity;

import java.util.ArrayList;
import java.util.List;

public class MaterialActivity extends TopicActivity {

    private static final String TAG = MaterialActivity.class.getSimpleName();

    private static final List<Class<? extends Fragment>> FRAGMENT_LIST = new ArrayList<Class<? extends Fragment>>();

    static {
        FRAGMENT_LIST.add(RecyclerViewFragment.class);
        FRAGMENT_LIST.add(CardViewFragment.class);
        FRAGMENT_LIST.add(ShadowsFragment.class);
        FRAGMENT_LIST.add(DrawablesFragment.class);
        FRAGMENT_LIST.add(CustomAnimationsFragment.class);
    }

    @Override
    protected List<Class<? extends Fragment>> getTopicFragments() {

        return FRAGMENT_LIST;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // must be called before adding content
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_material);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
