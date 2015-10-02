package com.vm.guides.common.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewUtil {

    public static View inflateListItemView(ViewGroup parent, int layoutId) {

        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }
}
