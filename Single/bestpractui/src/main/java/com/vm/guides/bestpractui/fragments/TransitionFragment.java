package com.vm.guides.bestpractui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vm.guides.bestpractui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransitionFragment extends Fragment {


    public TransitionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transition, container, false);
    }


}
