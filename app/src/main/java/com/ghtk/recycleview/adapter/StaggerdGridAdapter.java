package com.ghtk.recycleview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ghtk.recycleview.R;
import com.ghtk.recycleview.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by SonNM on 6/5/2022.
 */

public class StaggerdGridAdapter extends RecyclerView.Adapter<StaggerdGridAdapter.SimpleViewHolder> {

    private static final int COUNT = 20;

    private final Context mContext;
    private final List<String> mItems;
    private int mCurrentItemId = 0;
    Random mRandom = new Random();
    public StaggerdGridAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            addItem(i);
        }
    }

    @Override
    public StaggerdGridAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_staggerd, parent, false);
        return new StaggerdGridAdapter.SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StaggerdGridAdapter.SimpleViewHolder holder, final int position) {
        holder.title.setText(mItems.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = holder.getLayoutPosition();
                addItem(itemPosition + 1);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int itemPosition = holder.getLayoutPosition();
                removeItem(itemPosition);
                return true;
            }
        });
    }

    public void addItem(int position) {
        final int id = mCurrentItemId++;
        int countEnter = mRandom.nextInt(4);
        String text = id+"";
        for (int i = 0; i < countEnter; i++){
            text+="\n";
        }
        mItems.add(position, text);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        if (position < getItemCount()) {
            mItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }
}
