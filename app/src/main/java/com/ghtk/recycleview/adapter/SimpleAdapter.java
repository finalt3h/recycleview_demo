package com.ghtk.recycleview.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ghtk.recycleview.R;
import com.ghtk.recycleview.object.ItemObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SonNM on 6/5/2022.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

    private static final String TAG = SimpleAdapter.class.getSimpleName();
    private final Context mContext;
    private List<ItemObject> mData = new ArrayList<>();
    private OnCallback mOnCallback;

    public void setOnCallback(OnCallback mOnCallback) {
        this.mOnCallback = mOnCallback;
    }

    public SimpleAdapter(Context context, List<ItemObject> datas) {
        mContext = context;
        if (datas != null){
            mData = datas;
        }
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_simple, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        ItemObject itemObject = mData.get(position);
        holder.bindData(itemObject, position);
        Log.d(TAG, "onBindViewHolder: "+position);
    }

    public void add(ItemObject item, int position) {
        mData.add(position,item);
        notifyItemInserted(position);
    }


    public void remove(int position){
        if (position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView icAvatar;
        String name = "";
        public SimpleViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tv_title);
            icAvatar = view.findViewById(R.id.img_avatar);
        }
        public void bindData(ItemObject itemObject, int position){
            if(TextUtils.isEmpty((String) itemView.getTag())){
                itemView.setTag(itemObject.getName());
            }
            tvTitle.setText(itemObject.getName());
            icAvatar.setBackgroundColor(itemObject.getColor());


           itemView.setOnLongClickListener(v -> {
                if(mOnCallback!=null) mOnCallback.onRemoveItem(getAdapterPosition());
                return false;
            });
            itemView.setOnClickListener(v -> {
                if(mOnCallback!=null) mOnCallback.onAddItem(getAdapterPosition());
            });
            icAvatar.setOnClickListener(v -> {
                Toast.makeText(mContext, "Pos =" + position+", Name: "+itemView.getTag() + ", posItem "+getAdapterPosition() , Toast.LENGTH_SHORT).show();
            });
        }
    }

    public interface OnCallback{
        void onRemoveItem(int position);
        void onAddItem(int position);
    }
}
