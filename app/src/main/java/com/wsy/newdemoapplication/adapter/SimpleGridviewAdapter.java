package com.wsy.newdemoapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wsy.newdemoapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangSiYe on 2018/12/4.
 */
public class SimpleGridviewAdapter<T> extends BaseAdapter {

    private List<T> mList = new ArrayList<>();

    private LayoutInflater inflater;

    private Context mContext;

    public SimpleGridviewAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<T> databeans) {
        if (databeans == null
                || databeans.size() <= 0) {
            mList.clear();
        } else {
            mList.clear();
            mList.addAll(databeans);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_simple_gridview, null);
            holder.mTittle = convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        T t = mList.get(position);
        holder.mTittle.setText((String) t);

        return convertView;
    }

    class ViewHolder {

        TextView mTittle;
    }
}
