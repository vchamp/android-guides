package com.vm.guides.bestpractui.fragments;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

import com.vm.guides.bestpractui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShadowsFragment extends Fragment implements View.OnClickListener {


    public ShadowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shadows, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.jumpingButton).setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.jumpingButton) {
            final float translation = 28;
            final long duration = 500;
            final ViewPropertyAnimator animator = v.animate();
            animator.translationZBy(translation).setDuration(duration).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {

                    animator.translationZBy(-translation).setDuration(duration);
                }
            });
        }
    }
}
