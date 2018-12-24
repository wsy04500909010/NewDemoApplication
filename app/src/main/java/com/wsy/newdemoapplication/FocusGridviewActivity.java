package com.wsy.newdemoapplication;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wsy.newdemoapplication.adapter.RecyclerAdapter;
import com.wsy.newdemoapplication.adapter.SimpleGridviewAdapter;
import com.wsy.newdemoapplication.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangSiYe on 2018/12/4.
 */
public class FocusGridviewActivity extends BaseActivity {

    View gridFirstView, currentView;
    int currentPosition = -1;
    int currentPosition_recycler = -1;
    GridView gridView;
    Button btn, btn2;
    SimpleGridviewAdapter adapter;

    RecyclerView recyclerview;


    @Override
    protected void init() {
        gridView = findViewById(R.id.gridview);
        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);

        recyclerview = findViewById(R.id.recyclerview);

        final List<String> datas = new ArrayList<>();
        datas.add("1");
        datas.add("12");
        datas.add("13");
        datas.add("14");
        datas.add("16");
        datas.add("17");
        datas.add("18");
        datas.add("19");
        datas.add("10");

        adapter = new SimpleGridviewAdapter(this);

        gridView.setAdapter(adapter);
        adapter.setData(datas);

        gridView.setFocusable(false);
        btn.requestFocus();

        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!parent.isFocusable()) {
                    if (parent.getId() == R.id.gridview) {

                        view.findViewById(R.id.tv).setSelected(false);
                        gridFirstView = view;
                        currentPosition = position;

                        Log.e("focus:false", "currentposition==" + position);
                    }
                } else {
                    if (parent.getId() == R.id.gridview) {
                        currentView = view;
                        currentPosition = position;
                        Log.e("onItemSelected", "currentposition==" + position);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        GridLayoutManager lm = new GridLayoutManager(this, 3);
//        lm.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(lm);


        RecyclerAdapter<String> adapter = new RecyclerAdapter<>(datas);
        recyclerview.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                Toast.makeText(FocusGridviewActivity.this, datas.get((Integer) v.getTag()), Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnItemSelectedListener(new RecyclerAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View v, int position) {
                currentPosition_recycler = position;
                Log.e("recycler_current", "position====" + position);
            }
        });


    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_girdviewfocus);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {

            case KeyEvent.KEYCODE_DPAD_LEFT:
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (gridView.hasFocus() && currentPosition >= 6) {
                    gridView.setFocusable(false);
                    btn2.requestFocus();
                    return true;
                }
            case KeyEvent.KEYCODE_DPAD_UP:
                if (btn2.hasFocus()) {
                    gridView.setFocusable(true);
                    gridView.requestFocus();
                    gridView.setSelection(0);
                    return true;
                }
                break;
        }


        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();

        if (event.getAction() == KeyEvent.ACTION_UP) {

            //up事件,这里多数情况不需要处理.
//            Log.e("up", "action up");
//            return super.dispatchKeyEvent(event);
        } else {//down事件  

            //或许可以直接覆盖onKeyDown方法,而不是这个

            if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {

                Log.e("down", "KEYCODE_DPAD_UP");

                if (recyclerview.hasFocus() && currentPosition_recycler >= 3) {//第二行开始
                    View view = recyclerview.getLayoutManager()
                            .findViewByPosition(currentPosition_recycler - 3);
                    if (view != null) {
                        view.findViewById(R.id.tv).requestFocus();

                        return true;
                    }
                }


//                return false;
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                //处理事件
                Log.e("down", "KEYCODE_DPAD_LEFT");
                if (gridView.hasFocus()) {

                    Log.e("GridViewOnLeft", currentPosition + "");
                    if (currentPosition == 0 || currentPosition % 3 == 0) {//一行的第一个
                        gridView.setFocusable(false);
                        btn.requestFocus();

                        return true;
                    }
                } else if (recyclerview.hasFocus()) {
                    Log.e("RecyclerViewOnLeft", currentPosition + "");
                    if (currentPosition_recycler % 3 != 0) {//除第1列以外的列
                        View view = recyclerview.getLayoutManager()
                                .findViewByPosition(currentPosition_recycler - 1);
                        if (view != null) {
                            view.findViewById(R.id.tv).requestFocus();

                            return true;
                        }
                    }

                }

            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                //处理事件
                Log.e("down", "KEYCODE_DPAD_RIGHT");
                if (btn.hasFocus()) {
                    gridView.setFocusable(true);
                    gridView.requestFocus();
                    gridView.setSelection(0);

                    return true;
                } else if (gridView.hasFocus() && currentPosition % 3 == 2) {//一行的最后一个

                    return true;
                } else if (recyclerview.hasFocus()) {//除最后一列以外
                    if (currentPosition_recycler % 3 != 2) {
                        View view = recyclerview.getLayoutManager()
                                .findViewByPosition(currentPosition_recycler + 1);
                        if (view != null) {
                            view.findViewById(R.id.tv).requestFocus();

                            return true;
                        }
                    }
                }
//                return false;
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                Log.e("down", "KEYCODE_DPAD_DOWN");
                if (btn2.hasFocus()) {
                    recyclerview.requestFocus();

                    return true;
                } else if (recyclerview.hasFocus()) {
                    View view = recyclerview.getLayoutManager()
                            .findViewByPosition(currentPosition_recycler + 3);
                    if (view != null) {
                        view.findViewById(R.id.tv).requestFocus();

                        return true;
                    }
                }
//                return false;

            } else if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                Log.e("down", "KEYCODE_DPAD_CENTER||KeyEvent.KEYCODE_ENTER");
//                return false;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
