package com.vm.guides.bestpractui.fragments;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vm.guides.bestpractui.R;
import com.vm.guides.bestpractui.adapters.PaletteColorsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class DrawablesFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener, Palette
        .PaletteAsyncListener {

    private static final String TAG = DrawablesFragment.class.getSimpleName();

    private static final PorterDuff.Mode[] TINT_MODES = PorterDuff.Mode.values();

    private int currentTintAlpha;
    private int currentTintMode = 0;

    public DrawablesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drawables, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // tint
        ImageView tintImageView = (ImageView) view.findViewById(R.id.tintImageView);
        // find current tint mode
        for (int i = 0; i < TINT_MODES.length; i++) {
            if (TINT_MODES[i].equals(tintImageView.getImageTintMode())) {
                currentTintMode = i;
            }
        }
        Log.i(TAG, "current tint mode: " + TINT_MODES[currentTintMode]);
        // find current tint alpha
        currentTintAlpha = (tintImageView.getImageTintList().getDefaultColor() & (0xFF << 24)) >> 24;
        Log.i(TAG, "current tint alpha: " + currentTintAlpha);
        tintImageView.setOnClickListener(this);
        tintImageView.setOnLongClickListener(this);

        // palette
        RecyclerView paletteColorsView = (RecyclerView) getView().findViewById(R.id.paletteColorsView);
        paletteColorsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Palette.Builder paletteBuilder = Palette.from(((BitmapDrawable) tintImageView.getDrawable()).getBitmap());
        paletteBuilder.generate(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.tintImageView) {
            ImageView imageView = (ImageView) v;
            currentTintMode = (++currentTintMode) % TINT_MODES.length;
            Log.i(TAG, "current tint mode: " + TINT_MODES[currentTintMode]);
            imageView.setImageTintMode(TINT_MODES[currentTintMode]);
        }
    }

    @Override
    public boolean onLongClick(View v) {

        int id = v.getId();
        if (id == R.id.tintImageView) {
            ImageView imageView = (ImageView) v;
            ColorStateList colorStateList = imageView.getImageTintList();
            currentTintAlpha = (currentTintAlpha + 0xF) % 0xFF;
            Log.i(TAG, "current tint alpha: " + currentTintAlpha);
            imageView.setImageTintList(colorStateList.withAlpha(currentTintAlpha));
            return true;
        }
        return false;
    }

    @Override
    public void onGenerated(Palette palette) {

        RecyclerView paletteColorsView = (RecyclerView) getView().findViewById(R.id.paletteColorsView);
        List<Pair<String, Palette.Swatch>> swatches = new ArrayList<>();
        swatches.add(new Pair<>("Vibrant", palette.getVibrantSwatch()));
        swatches.add(new Pair<>("Dark vibrant", palette.getDarkVibrantSwatch()));
        swatches.add(new Pair<>("Light vibrant", palette.getLightVibrantSwatch()));
        swatches.add(new Pair<>("Muted", palette.getMutedSwatch()));
        swatches.add(new Pair<>("Dark muted", palette.getDarkMutedSwatch()));
        swatches.add(new Pair<>("Light muted", palette.getLightMutedSwatch()));
        paletteColorsView.setAdapter(new PaletteColorsAdapter(swatches));
    }
}
