package com.vm.guides;

import android.app.Fragment;

import com.vm.guides.fragments.ViewStubFragment;

import java.util.ArrayList;
import java.util.List;

public class Fragments {

    public static final List<Class<? extends Fragment>> FRAGMENT_LIST = new ArrayList<Class<? extends Fragment>>();

    static {
        FRAGMENT_LIST.add(ViewStubFragment.class);
    }
}
