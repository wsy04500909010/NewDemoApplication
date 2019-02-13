package com.wsy.newdemoapplication.proxy;

import android.view.View;
import android.widget.Toast;

/**
 * Created by WangSiYe on 2019/2/12.
 */
public class HookedClickListenerProxy implements View.OnClickListener {



    private View.OnClickListener origin;

    public HookedClickListenerProxy(View.OnClickListener origin) {
        this.origin = origin;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Hook Click Listener", Toast.LENGTH_SHORT).show();
        if (origin != null) {
            origin.onClick(v);
        }
    }
}
