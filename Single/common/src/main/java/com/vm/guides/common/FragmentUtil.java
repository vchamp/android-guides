package com.vm.guides.common;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class FragmentUtil {

    public static void addFragment(Activity activity, Class<? extends Fragment> fragmentClass, int fragmentContainerId) {
        try {
            Fragment fragment = fragmentClass.newInstance();
            //            To make fragment transactions in your activity (such as add,
            // remove, or
            // replace a fragment), you must use APIs from FragmentTransaction
            //            http://developer.android.com/guide/components/fragments.html
            FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
            fragmentTransaction.add(fragmentContainerId, fragment);
            fragmentTransaction.addToBackStack("Undo");
            fragmentTransaction.commit();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
