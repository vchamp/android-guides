package com.vm.guides.bestpractui.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vm.guides.bestpractui.R;
import com.vm.guides.bestpractui.views.CustomView;

/**
 * http://developer.android.com/training/custom-views/create-view.html
 */
public class CustomViewClassFragment extends Fragment implements View.OnClickListener {


    public CustomViewClassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_view_class, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.changeColorButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.changeColorButton) {
            changeColor();
        }
    }

    private void changeColor() {

        CustomView customView = (CustomView) getView().findViewById(R.id.customView);
        customView.setExampleColor(Color.GREEN);
    }
}
