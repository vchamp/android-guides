package com.vm.transitions;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class AlphaTransition extends Transition {

    private static final String PROPERTY_ALPHA = "alpha";
    private static final String[] PROPERTIES = {PROPERTY_ALPHA};

    @Override
    public String[] getTransitionProperties() {
        return PROPERTIES;
    }

    private void captureValues(TransitionValues transitionValues) {
        float alpha = transitionValues.view.getAlpha();
        Log.d("TEMP", "capture alpha: " + alpha);
        transitionValues.values.put(PROPERTY_ALPHA, alpha);
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null ||
                !startValues.values.containsKey(PROPERTY_ALPHA) ||
                !endValues.values.containsKey(PROPERTY_ALPHA)) {
            Log.d("TEMP", "smth is null");
            return null;
        }

        final float startAlpha = (Float) startValues.values.get(PROPERTY_ALPHA);
        final float endAlpha = (Float) endValues.values.get(PROPERTY_ALPHA);
        Log.d("TEMP", "alphas: " + startAlpha + ", " + endAlpha);

        final View view = endValues.view;
        view.setAlpha(startAlpha);
        return ObjectAnimator.ofFloat(view, View.ALPHA, startAlpha, endAlpha);
    }
}
