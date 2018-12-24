package com.wsy.newdemoapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wsy.newdemoapplication.R;

import butterknife.BindView;

/**
 * Created by WangSiYe on 2018/12/24.
 */
public class MyFragment extends Fragment {

    @BindView(R.id.tv)
    TextView textView;

    public MyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my, null);

        textView = rootView.findViewById(R.id.tv);
        textView.setText("当前是第" + getArguments().get("key") + "页");


        return rootView;
    }
}
