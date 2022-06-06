package com.ghtk.recycleview.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ghtk.recycleview.R;
import com.ghtk.recycleview.object.ItemObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SonNM on 6/5/2022.
 */

public class CustomizeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_CUSTOM = 2;
    private static final String TAG = CustomizeAdapter.class.getSimpleName();
    private final Context mContext;
    private List<ItemObject> mData = new ArrayList<>();
    private CustomizeAdapter.OnCallback mOnCallback;

    public void setOnCallback(CustomizeAdapter.OnCallback mOnCallback) {
        this.mOnCallback = mOnCallback;
    }

    public void add(ItemObject item, int position) {
        mData.add(position,item);
        notifyItemInserted(position);
    }

    public void add(ItemObject item) {
        mData.add(item);
        notifyItemInserted(getItemCount()-1);
    }

    public void remove(int position){
        if (position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }



    public CustomizeAdapter(Context context, List<ItemObject> datas) {
        mContext = context;
        if (datas != null){
            mData = datas;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position%3 == 0){
            return TYPE_CUSTOM;
        }else {
            return TYPE_NORMAL;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view;
        if(viewType == TYPE_NORMAL){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_simple, parent, false);
            return new CustomizeAdapter.SimpleViewHolder(view);
        }else if(viewType == TYPE_CUSTOM){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_custom_1, parent, false);
            return new CustomizeAdapter.CustomViewHolder(view);
        }else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_simple, parent, false);
            return new CustomizeAdapter.SimpleViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemObject itemObject = mData.get(position);
        if(holder instanceof SimpleViewHolder){
            ((SimpleViewHolder)holder).bindData(itemObject, position);
        }else if(holder instanceof CustomViewHolder){
            ((CustomViewHolder)holder).bindData(itemObject, position);
        }

        Log.d(TAG, "onBindViewHolder: "+position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView icAvatar;
        public SimpleViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tv_title);
            icAvatar = view.findViewById(R.id.img_avatar);
        }
        public void bindData(ItemObject itemObject, int position){
            /** lưu tên cho viewholder **/
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
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitleCus;
        public ImageView icAvatarCus;
        public Button btnHide;
        public CustomViewHolder(View view) {
            super(view);
            tvTitleCus = view.findViewById(R.id.tv_title_cus);
            icAvatarCus = view.findViewById(R.id.img_avatar_cus);
            btnHide = view.findViewById(R.id.btnHide);
        }
        public void bindData(ItemObject itemObject, int position){
            /** lưu tên cho viewholder **/
            if(TextUtils.isEmpty((String) itemView.getTag())){
                itemView.setTag(itemObject.getName());
            }
            tvTitleCus.setText(itemObject.getName());
            icAvatarCus.setBackgroundColor(itemObject.getColor());

            if(itemObject.isSelected()){
                btnHide.setVisibility(View.VISIBLE);
            }else {
                btnHide.setVisibility(View.GONE);
            }

            itemView.setOnLongClickListener(v -> {
                if(mOnCallback!=null) mOnCallback.onRemoveItem(getAdapterPosition());
                return false;
            });
            itemView.setOnClickListener(v -> {
                if(mOnCallback!=null) mOnCallback.onAddItem(getAdapterPosition());
            });
            icAvatarCus.setOnClickListener(v -> {
                itemObject.setSelected(!itemObject.isSelected());
                if(itemObject.isSelected()){
                    btnHide.setVisibility(View.VISIBLE);
                }else {
                    btnHide.setVisibility(View.GONE);
                }
            });
        }
    }

    public interface OnCallback{
        void onRemoveItem(int position);
        void onAddItem(int position);
    }
}
