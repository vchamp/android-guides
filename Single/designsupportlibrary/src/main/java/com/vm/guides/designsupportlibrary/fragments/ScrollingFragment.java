package com.vm.guides.designsupportlibrary.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.vm.guides.designsupportlibrary.R;
import com.vm.guides.designsupportlibrary.adapters.SimplestAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScrollingFragment extends Fragment {


    public ScrollingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scrolling, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        populateRecyclerView();
    }

    private void populateRecyclerView() {

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String[] items = new String[100];
        for (int i = 0; i < items.length; i++) {
            items[i] = String.valueOf(i + 1);
        }
        recyclerView.setAdapter(new SimplestAdapter(items));
    }
}
