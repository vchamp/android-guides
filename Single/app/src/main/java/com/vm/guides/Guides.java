package com.vm.guides;

import android.app.Activity;

import com.vm.guides.bestpractui.BestPractUiActivity;

import java.util.ArrayList;
import java.util.List;

public class Guides {

    public static final List<Class<? extends Activity>> ACTIVITY_LIST = new ArrayList<Class<? extends Activity>>();

    static {
        ACTIVITY_LIST.add(BestPractUiActivity.class);
    }
}
