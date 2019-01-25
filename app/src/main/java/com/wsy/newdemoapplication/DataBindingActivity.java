package com.wsy.newdemoapplication;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.Toast;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.bean.NormalSimpleBean;
import com.wsy.newdemoapplication.databinding.ActivityDatabindingBinding;

/**
 * Created by WangSiYe on 2019/1/23.
 */
public class DataBindingActivity extends BaseActivity {

    //这个类是根据加载的layout文件名称生成的  需要build一次才能生成
    ActivityDatabindingBinding dataBinding;

    NormalSimpleBean normalSimpleBean;

    @Override
    protected void init() {
        normalSimpleBean = new NormalSimpleBean("李大安", "都是佛说金佛山副教授");


//        dataBinding.tv.setText(normalSimpleBean.getName());
//        dataBinding.tv2.setText(normalSimpleBean.getAddress());

        //这个方法是 自动生成的 set+ xml中variety生命的name 首字母大写
        dataBinding.setSimplebean(normalSimpleBean);
        //通用方法 效果一样
//        dataBinding.setVariable(BR.simplebean,normalSimpleBean);

        dataBinding.setPresenter(new Presenter());
    }

    @Override
    public void setCustomView() {

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);


    }

    public class Presenter {

        public void onTextChanged(CharSequence s, int start, int before, int count) {

            normalSimpleBean.setName(s.toString());

            normalSimpleBean.isFired.set(!normalSimpleBean.isFired.get());
            dataBinding.setSimplebean(normalSimpleBean);

        }

        public void onClick(View v) {
            Toast.makeText(DataBindingActivity.this, "点击了", Toast.LENGTH_SHORT).show();
        }

        public void onClickListenerBinding(NormalSimpleBean normalSimpleBean) {
            Toast.makeText(DataBindingActivity.this, normalSimpleBean.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
