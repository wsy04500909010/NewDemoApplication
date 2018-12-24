package com.wsy.newdemoapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wsy.newdemoapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangSiYe on 2018/12/10.
 * 测试更改
 */
public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    public interface OnItemClickListener {
        void onItemClick(View v);
    }

    public interface OnItemSelectedListener {
        void onItemSelected(View v, int position);
    }

    private List<T> mList = new ArrayList<>();


    public RecyclerAdapter(List<T> datas) {
        mList.clear();
        mList.addAll(datas);
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemSelectedListener onItemSelectedListener;

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_gridview, parent, false);
        ViewHolder holder = new ViewHolder(view);

        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.ViewHolder holder, final int position) {
        String content = (String) mList.get(position);
        holder.tv.setText(content);

        holder.tv.setTag(position);

        /** 设置Item的Selected事件 */
        holder.tv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("RecyclerAdapter", "onFocusChange hasFocus:" + hasFocus + ",position:" + position);
                if (hasFocus) {
                    if (onItemSelectedListener != null) {
                        onItemSelectedListener.onItemSelected(holder.itemView, position);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
