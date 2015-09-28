package com.vm.guides.bestpractui.fragments;

import android.app.Fragment;

import com.vm.guides.bestpractui.fragments.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.List;

public class Fragments {

    public static final List<Class<? extends Fragment>> FRAGMENT_LIST = new ArrayList<Class<? extends Fragment>>();

    static {
        FRAGMENT_LIST.add(RecyclerViewFragment.class);
        FRAGMENT_LIST.add(CardViewFragment.class);
        FRAGMENT_LIST.add(ShadowsFragment.class);
    }
}
