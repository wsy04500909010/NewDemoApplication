package com.wsy.newdemoapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wsy.newdemoapplication.base.BaseActivity;

import greendaobean.TestGreenDaoBean;

import com.wsy.newdemoapplication.dao.GreenDaoManager;
import com.wsy.newdemoapplication.dao.TestGreenDaoBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import butterknife.BindView;
import greendaobean.User;

/**
 * Created by WangSiYe on 2018/12/19.
 */
public class GreenDaoActivity extends BaseActivity {

    @BindView(R.id.btn_insert)
    Button btn_insert;
    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.tv_result)
    TextView tv_result;
    @BindView(R.id.btn_getSMS_no)
    Button btn_getSMS_no;
    @BindView(R.id.tv_result_sms)
    TextView tv_result_sms;
    @BindView(R.id.btn_add_user)
    Button btn_add_user;
    @BindView(R.id.btn_fix_user)
    Button btn_fix_user;
    @BindView(R.id.tv_result_user)
    TextView tv_result_user;
    @BindView(R.id.btn_delete_user)
    Button btn_delete_user;

    GreenDaoManager gm;
    int REQUEST_SMS_PERMISSION = 1;
    int REQUEST_WRITESTORAGE_PERMISSION = 2;


    int index = 0;

    @Override
    protected void init() {
        initData();


        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestGreenDaoBean testGreenDaoBean = new TestGreenDaoBean();
                testGreenDaoBean.setDate("201-03-11_" + index);
                testGreenDaoBean.setSportId(2l);
                testGreenDaoBean.setStep(index);

                gm.getSession().getTestGreenDaoBeanDao().insert(testGreenDaoBean);

                index++;
            }
        });

        btn_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(null, "姓名" + index, Long.valueOf(index), index + "班");

                gm.getSession().getUserDao().insert(user);

                index++;
            }
        });

        btn_fix_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(2l, "英雄" + index, Long.valueOf(index), index + "班");

                gm.getSession().getUserDao().update(user);
                index++;
            }
        });

        btn_delete_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gm.getSession().getUserDao().deleteByKey(1l);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestGreenDaoBeanDao testGreenDaoBeanDao = gm.getSession().getTestGreenDaoBeanDao();
                QueryBuilder<TestGreenDaoBean> qb = testGreenDaoBeanDao.queryBuilder();
                qb.where(TestGreenDaoBeanDao.Properties.Step.eq(1)).orderAsc(TestGreenDaoBeanDao.Properties.Id);
                List<TestGreenDaoBean> list = qb.list();

                tv_result.setText(list.size() > 0 ? list.get(0).getDate() : "无结果");
            }
        });

        btn_getSMS_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(GreenDaoActivity.this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
                    String sms_inbox = "content://sms/inbox";
                    Uri uri = Uri.parse(sms_inbox);
                    String[] projection = new String[]{"address", "date"};
                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    tv_result_sms.setText("总共有" + cursor.getCount() + "条");

                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(GreenDaoActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {

                        requestPermissions(new String[]{Manifest.permission.READ_SMS}, REQUEST_SMS_PERMISSION);
                    }
                }


            }
        });
    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_greendao);
    }

    private void initData() {
        if (ContextCompat.checkSelfPermission(GreenDaoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            gm = GreenDaoManager.getInstance();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITESTORAGE_PERMISSION);
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(GreenDaoActivity.this, "权限已申请", Toast.LENGTH_SHORT).show();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS)) {//当拒绝 并没有勾选下次不提醒选项时  这里为true 其他均为false
                        Toast.makeText(GreenDaoActivity.this, "权限已拒绝", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GreenDaoActivity.this, "权限已拒绝,请手动打开", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            return;
        } else if (requestCode == REQUEST_WRITESTORAGE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(GreenDaoActivity.this, "权限已申请", Toast.LENGTH_SHORT).show();

                gm = GreenDaoManager.getInstance();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {//当拒绝 并没有勾选下次不提醒选项时  这里为true 其他均为false
                        Toast.makeText(GreenDaoActivity.this, "权限已拒绝", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GreenDaoActivity.this, "权限已拒绝,请手动打开", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
