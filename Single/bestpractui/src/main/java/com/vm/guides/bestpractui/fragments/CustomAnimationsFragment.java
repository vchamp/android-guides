package com.vm.guides.bestpractui.fragments;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import com.vm.guides.bestpractui.R;
import com.vm.guides.common.FragmentUtil;

/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class CustomAnimationsFragment extends Fragment implements View.OnClickListener {

    public CustomAnimationsFragment() {

        super();
        setEnterTransition(new Explode());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_animations, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.revealButton).setOnClickListener(this);
        view.findViewById(R.id.hideButton).setOnClickListener(this);
        view.findViewById(R.id.revealImage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.revealButton) {
            revealImage();
        } else if (id == R.id.hideButton) {
            hideImage();
        } else if (id == R.id.revealImage) {
            setExitTransition(new ChangeImageTransform());

            FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment, new TransitionFragment());
            fragmentTransaction.addSharedElement(v, "logo");
            fragmentTransaction.addToBackStack("Undo");
            fragmentTransaction.commit();

//            FragmentUtil.addFragment(getActivity(), TransitionFragment.class, R.id.fragment, true);
        }
    }

    private void revealImage() {

        // previously invisible view
        View myView = getView().findViewById(R.id.revealImage);

        // get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }

    private void hideImage() {

        // previously visible view
        final View myView = getView().findViewById(R.id.revealImage);

        // get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

        // get the initial radius for the clipping circle
        int initialRadius = myView.getWidth();

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });

        // start the animation
        anim.start();
    }
}
