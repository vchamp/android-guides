package com.vm.guides.bestpractui.fragments;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;

import com.vm.guides.bestpractui.R;
import com.vm.guides.bestpractui.activities.TransitionActivity;

/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class CustomAnimationsFragment extends Fragment implements View.OnClickListener {

    public CustomAnimationsFragment() {

        super();
//        setEnterTransition(new Explode());
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
        view.findViewById(R.id.moveButton).setOnClickListener(this);
        view.findViewById(R.id.revealImage).setOnClickListener(this);
        view.findViewById(R.id.stateListAnimationButton).setOnClickListener(this);

        ImageView vectorImage = (ImageView) view.findViewById(R.id.animVectorDrawableImageView);
        ((AnimatedVectorDrawable) vectorImage.getDrawable()).start();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.revealButton) {
            revealImage();
        } else if (id == R.id.hideButton) {
            hideImage();
        } else if (id == R.id.moveButton) {
            moveImage();
        } else if (id == R.id.revealImage) {
            doTransition();
        } else if (id == R.id.stateListAnimationButton) {

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

    private void moveImage() {

        // misunderstanding

        final View image = getView().findViewById(R.id.revealImage);

        PathInterpolator interpolator = new PathInterpolator(0.4f, 0.2f, 1, 1);
        Path path = new Path();
        path.moveTo(0.25f, 0.5f);
        path.lineTo(1f, 1f);
//
        ObjectAnimator animator = ObjectAnimator.ofFloat(image, View.TRANSLATION_X, View.TRANSLATION_Y, path);
//        animator.setFloatValues(100, 100);
        animator.setValues(PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 200));
        animator.setValues(PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 100));
        animator.setDuration(1000);
        animator.setInterpolator(interpolator);
        animator.start();
    }

    private void doTransition() {

        View sharedView = getView().findViewById(R.id.revealImage);
        // fragment transition doesn't work

        //            setSharedElementReturnTransition(TransitionInflater.from(getActivity()).inflateTransition(R
        // .transition
        //                    .change_image_transform));
        //            setExitTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R
        // .transition.fade));
        //
        //            TransitionFragment fragment = new TransitionFragment();
        //            fragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity())
        // .inflateTransition(R
        //                    .transition.change_image_transform));
        //            fragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition
        // (android.R.transition.fade));
        //
        //            FragmentTransaction fragmentTransaction = getActivity().getFragmentManager()
        // .beginTransaction();
        //            fragmentTransaction.add(R.id.fragment, fragment);
        //            fragmentTransaction.addToBackStack("Undo");
        //            fragmentTransaction.addSharedElement(v, "logo");
        //            fragmentTransaction.commit();

        //            FragmentUtil.addFragment(getActivity(), TransitionFragment.class, R.id.fragment, true);

        Intent intent = new Intent(getActivity(), TransitionActivity.class);
        // create the transition animation - the images in the layouts
        // of both activities are defined with android:transitionName="robot"
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), sharedView, "logo");
        // start the new activity
        getActivity().startActivity(intent, options.toBundle());
    }
}
