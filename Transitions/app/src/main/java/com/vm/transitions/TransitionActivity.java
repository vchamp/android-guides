package com.vm.transitions;

import android.view.View;

public interface TransitionActivity {

    void registerForTransition(View view);

    void freeToRunEnterTransition();
}
