package com.vm.transitions;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity implements TransitionActivity {

    private List<View> mTransitionAwaitingViews;

    @Override
    public void registerForTransition(final View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        if (mTransitionAwaitingViews == null) {
            mTransitionAwaitingViews = new ArrayList<>();
        }
        mTransitionAwaitingViews.add(view);
        if (view instanceof ImageView) {
            view.getViewTreeObserver().addOnPreDrawListener(
                    new ViewTreeObserver.OnPreDrawListener() {
                        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public boolean onPreDraw() {
                            view.getViewTreeObserver().removeOnPreDrawListener(this);
                            mTransitionAwaitingViews.remove(view);
                            if (mTransitionAwaitingViews.isEmpty()) {
                                startPostponedEnterTransition();
                            }
                            return true;
                        }
                    });
        } else {
            view.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onGlobalLayout() {
                            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            mTransitionAwaitingViews.remove(view);
                            if (mTransitionAwaitingViews.isEmpty()) {
                                startPostponedEnterTransition();
                            }
                        }
                    });
        }
    }

    @Override
    public void freeToRunEnterTransition() {
        if (mTransitionAwaitingViews == null || mTransitionAwaitingViews.isEmpty()) {
            startPostponedEnterTransition();
        }
    }
}
