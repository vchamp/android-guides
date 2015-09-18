package com.vm.guides.bestpractui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vm.guides.bestpractui.R;

public class SimplestAdapter extends RecyclerView.Adapter<SimplestAdapter.ViewHolder> {

    private String[] dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(TextView v) {

            super(v);
            textView = v;
        }
    }

    public SimplestAdapter(String[] dataset) {

        super();
        this.dataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout
                .list_item_simple_text, parent, false);

        // set the view's size, margins, paddings and layout parameters
        //        ...

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(dataset[position]);
    }

    @Override
    public int getItemCount() {

        return dataset.length;
    }
}
