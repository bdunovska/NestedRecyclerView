package com.example.belladunovska.nestedrecyclerview;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by belladunovska on 17/07/15.
 */
public class MyVerticalAdapter extends RecyclerView.Adapter<MyVerticalAdapter.ViewHolder> {
    private final ArrayList<String> dataset;
    private Context context;
    private Map<Integer, Parcelable> scrollStatePositionsMap = new HashMap<>();

    public MyVerticalAdapter(Context context, ArrayList<String> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    @Override
    public MyVerticalAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent,
                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vertical_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ArrayList<String> dataset1 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataset1.add(String.valueOf(i));
        }
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setAdapter(new MyHorizontalAdapter(context, dataset1));
        holder.setPosition(position);
        if (scrollStatePositionsMap.containsKey(position)) {
            holder.recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    holder.recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                    holder.recyclerView.getLayoutManager().onRestoreInstanceState(scrollStatePositionsMap.get(position));
                    return false;
                }
            });
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public RecyclerView recyclerView;

        public void setPosition(int position) {
            this.position = position;
        }

        public int position;

        public ViewHolder(final View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.horizontal_grid_view);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        scrollStatePositionsMap.put(position, recyclerView.getLayoutManager().onSaveInstanceState());
                    }
                }
            });
        }

    }
}