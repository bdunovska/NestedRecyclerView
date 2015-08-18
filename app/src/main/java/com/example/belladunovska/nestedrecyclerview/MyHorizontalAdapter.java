package com.example.belladunovska.nestedrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by belladunovska on 17/07/15.
 */
public class MyHorizontalAdapter extends RecyclerView.Adapter<MyHorizontalAdapter.ViewHolder> {
    private ArrayList<String> dataset;
    private Context context;

    public MyHorizontalAdapter(Context context, ArrayList<String> dataset) {
        this.context = context;
        this.dataset = dataset;
    }


    @Override
    public MyHorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.horizontalItemTextView.setText(dataset.get(position));
        Picasso.with(context).load("http://lorempixel.com/400/200/sports/").networkPolicy(NetworkPolicy.NO_CACHE).into(holder.randomImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView randomImageView;
        public TextView horizontalItemTextView;

        public ViewHolder(View v) {
            super(v);
            horizontalItemTextView = (TextView) v.findViewById(R.id.horizontal_item_text_view);
            randomImageView = (ImageView) v.findViewById(R.id.random_image_view);
        }
    }
}