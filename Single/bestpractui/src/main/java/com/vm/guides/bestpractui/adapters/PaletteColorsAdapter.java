package com.vm.guides.bestpractui.adapters;

import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vm.guides.bestpractui.R;
import com.vm.guides.common.util.ViewUtil;

import java.util.List;

public class PaletteColorsAdapter extends RecyclerView.Adapter<PaletteColorsAdapter.ViewHolder> {

    private List<Pair<String, Palette.Swatch>> swatches;

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewGroup itemView;
        public TextView titleTextView;
        public TextView bodyTextView;
        public View colorView;

        public ViewHolder(ViewGroup v) {

            super(v);
            itemView = v;
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            bodyTextView = (TextView) v.findViewById(R.id.bodyTextView);
            colorView = v.findViewById(R.id.colorView);
        }
    }

    public PaletteColorsAdapter(List<Pair<String, Palette.Swatch>> swatches) {

        super();
        this.swatches = swatches;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder((ViewGroup) ViewUtil.inflateListItemView(parent, R.layout.list_item_palette_color));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Pair<String, Palette.Swatch> swatchItem = swatches.get(position);
        holder.titleTextView.setText(swatchItem.first);
        Palette.Swatch swatch = swatchItem.second;
        if (swatch != null) {
            holder.itemView.setBackgroundColor(swatch.getRgb());
            holder.titleTextView.setTextColor(swatch.getTitleTextColor());
            holder.bodyTextView.setText(swatch.toString());
            holder.bodyTextView.setTextColor(swatch.getBodyTextColor());
        } else {
            holder.itemView.setBackgroundColor(Color.BLACK);
            holder.titleTextView.setTextColor(Color.WHITE);
            holder.bodyTextView.setText("Not available");
            holder.bodyTextView.setTextColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {

        return swatches.size();
    }
}
