package com.vm.guides.designsupportlibrary.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vm.guides.designsupportlibrary.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends Fragment {


    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Tab 1"));
        tabs.addTab(tabs.newTab().setText("Tab 2"));
        tabs.addTab(tabs.newTab().setText("Tab 3"));
    }
}
