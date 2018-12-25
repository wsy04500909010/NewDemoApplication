package com.wsy.newdemoapplication;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.dao.GreenDaoManager;
import com.wsy.newdemoapplication.dao.LeaderBeanDao;
import com.wsy.newdemoapplication.dao.MemberBeanDao;
import com.wsy.newdemoapplication.dao.OtherUserInfoBeanDao;
import com.wsy.newdemoapplication.dao.UserBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import greendaobean.LeaderBean;
import greendaobean.MemberBean;
import greendaobean.OtherUserInfoBean;
import greendaobean.UserBean;

/**
 * Created by WangSiYe on 2018/12/24.
 */
public class GreenDao2Activity extends BaseActivity {

    @BindView(R.id.btn_1)
    Button btn_1;
    @BindView(R.id.btn_2)
    Button btn_2;


    @BindView(R.id.btn_insert_1to1)
    Button btn_insert_1to1;
    @BindView(R.id.btn_search_1to1)
    Button btn_search_1to1;

    @BindView(R.id.ll_1to1)
    LinearLayout ll_1to1;
    @BindView(R.id.ll_1tomany)
    LinearLayout ll_1tomany;

    @BindView(R.id.tv_result_userbean)
    TextView tv_result_userbean;
    @BindView(R.id.tv_result_otheruserinfobean)
    TextView tv_result_otheruserinfobean;

    @BindView(R.id.btn_insert_1tomany)
    Button btn_insert_1tomany;
    @BindView(R.id.btn_search_1tomany)
    Button btn_search_1tomany;


    @BindView(R.id.tv_result_leaderbean)
    TextView tv_result_leaderbean;
    @BindView(R.id.tv_result_memberbean)
    TextView tv_result_memberbean;

    @BindView(R.id.et_id_userbean)
    EditText et_id_userbean;
    @BindView(R.id.btn_delete_userbean_1to1)
    Button btn_delete_userbean_1to1;
    @BindView(R.id.et_id_otheruserinfobean)
    EditText et_id_otheruserinfobean;
    @BindView(R.id.btn_delete_otheruserinfobean_1to1)
    Button btn_delete_otheruserinfobean_1to1;


    @Override
    protected void init() {

        btn_1.setOnClickListener(v -> {
            ll_1to1.setVisibility(View.VISIBLE);
            ll_1tomany.setVisibility(View.GONE);
        });
        btn_2.setOnClickListener(v -> {
            ll_1to1.setVisibility(View.GONE);
            ll_1tomany.setVisibility(View.VISIBLE);
        });


        btn_insert_1to1.setOnClickListener((v) -> {
            UserBean user = new UserBean();
            user.setAge((int) (Math.random() * 100 + 10));
            user.setIdCard((int) (Math.random() * 10000) + "123456789");
            if (Math.random() * 100 > 50) {
                user.setIsMale(true);
            } else {
                user.setIsMale(false);
            }
            user.setName("李看啥" + (int) (Math.random() * 1000));
            user.setProvince("shanhaijing");
            Long otherUserInfoId = (long) (Math.random() * 1000);

            //只setOtherUserInfoId，不setOtherUserInfoBean
            user.setOtherUserInfoId(otherUserInfoId);

            //insert user
            GreenDaoManager.getInstance().getSession().getUserBeanDao().insert(user);


            OtherUserInfoBean otherUserInfoBean = new OtherUserInfoBean();

            //主键id使用user的otherUserInfoId
            otherUserInfoBean.setId(otherUserInfoId);

            otherUserInfoBean.setTel(String.valueOf((100) * 19 + Math.random() * 100));
            otherUserInfoBean.setAddress("dongshanjing");
            Date date = new Date();
            date.setTime((long) (System.currentTimeMillis() - Math.random() * 2000000000));
            otherUserInfoBean.setBirthday(date);

            //insert otherUserInfoBean
            GreenDaoManager.getInstance().getSession().getOtherUserInfoBeanDao().insert(otherUserInfoBean);

            //查询并显示
            search1to1();


        });

        btn_insert_1tomany.setOnClickListener(v -> {
            for (int i = 0; i < 2; i++) {
                LeaderBean leader = new LeaderBean();
                leader.setName("张吃啥" + (int) (Math.random() * 1000));

                GreenDaoManager.getInstance().getSession().getLeaderBeanDao().insert(leader);

                List<MemberBean> memberBeans = new ArrayList<>();
                for (int j = 0; j < 3; j++) {

                    MemberBean memberBean = new MemberBean();

                    //setLeaderId，值为其所属的leader的id（leaderBean保存完成后，缓存即可获取到数据库中生成的主键id）
                    memberBean.setLeaderId(leader.getId());
                    memberBean.setName("赵小" + (int) (Math.random() * 1000));

//                    GreenDaoManager.getInstance().getSession().getMemberBeanDao().insert(memberBean);
                    memberBeans.add(memberBean);
                }
                //这里使用批量插入的方式
                GreenDaoManager.getInstance().getSession().getMemberBeanDao().insertInTx(memberBeans);

                search1tomany();
            }
        });


        btn_search_1to1.setOnClickListener(v -> {
            search1to1();
        });

        btn_search_1tomany.setOnClickListener(v -> {
            search1tomany();
        });

        btn_delete_userbean_1to1.setOnClickListener((v -> {
            if (et_id_userbean.getText().toString().equals("")) {
                return;
            } else {
                GreenDaoManager.getInstance().getSession().getUserBeanDao().deleteByKey(Long.valueOf(et_id_userbean.getText().toString()));
                search1to1();
            }
        }));

        btn_delete_otheruserinfobean_1to1.setOnClickListener((v -> {
            if (et_id_otheruserinfobean.getText().toString().equals("")) {
                return;
            } else {
                GreenDaoManager.getInstance().getSession().getOtherUserInfoBeanDao().deleteByKey(Long.valueOf(et_id_otheruserinfobean.getText().toString()));
                search1to1();
            }
        }));
    }

    private void search1tomany() {
        //            查询并显示
        LeaderBeanDao leaderBeanDao = GreenDaoManager.getInstance().getSession().getLeaderBeanDao();
        QueryBuilder<LeaderBean> qb = leaderBeanDao.queryBuilder();
        qb.build();
        List<LeaderBean> list = qb.list();

        if (list.size() > 0) {
            StringBuffer sb = new StringBuffer();
            Iterator<LeaderBean> it = list.iterator();
            while (it.hasNext()) {
                LeaderBean leaderBean = it.next();
                sb.append(leaderBean.getId() + "----" + leaderBean.getName() + "\n");
            }
            tv_result_leaderbean.setText(sb.toString());
        } else {
            tv_result_leaderbean.setText("无结果");
        }

        MemberBeanDao memberBeanDao = GreenDaoManager.getInstance().getSession().getMemberBeanDao();
        QueryBuilder<MemberBean> qb2 = memberBeanDao.queryBuilder();
        qb2.build();
        List<MemberBean> list2 = qb2.list();

        if (list2.size() > 0) {
            StringBuffer sb = new StringBuffer();
            Iterator<MemberBean> it = list2.iterator();
            while (it.hasNext()) {
                MemberBean memberBean = it.next();
                sb.append(memberBean.getId() + "----" + memberBean.getName() + "leaderid==" + memberBean.getLeaderId() + "\n");
            }
            tv_result_memberbean.setText(sb.toString());
        } else {
            tv_result_memberbean.setText("无结果");
        }
    }

    private void search1to1() {
        //            查询并显示
        UserBeanDao userBeanDao = GreenDaoManager.getInstance().getSession().getUserBeanDao();
        QueryBuilder<UserBean> qb = userBeanDao.queryBuilder();
        qb.build();
        List<UserBean> list = qb.list();

        if (list.size() > 0) {
            StringBuffer sb = new StringBuffer();
            Iterator<UserBean> it = list.iterator();
            while (it.hasNext()) {
                UserBean userBean = it.next();
                sb.append(userBean.getId() + "----" + userBean.getName() + "\n");
            }
            tv_result_userbean.setText(sb.toString());
        } else {
            tv_result_userbean.setText("无结果");
        }

        OtherUserInfoBeanDao otherUserInfoBeanDao = GreenDaoManager.getInstance().getSession().getOtherUserInfoBeanDao();
        QueryBuilder<OtherUserInfoBean> qb2 = otherUserInfoBeanDao.queryBuilder();
        qb2.build();
        List<OtherUserInfoBean> list2 = qb2.list();

        if (list2.size() > 0) {
            StringBuffer sb = new StringBuffer();
            Iterator<OtherUserInfoBean> it = list2.iterator();
            while (it.hasNext()) {
                OtherUserInfoBean otherUserInfoBean = it.next();
                sb.append(otherUserInfoBean.getId() + "----" + otherUserInfoBean.getBirthday() + "\n");
            }
            tv_result_otheruserinfobean.setText(sb.toString());
        } else {
            tv_result_otheruserinfobean.setText("无结果");
        }


    }

    /**
     * 查询其他相关写法
     * List<Note> list1 = noteDao.queryRaw("where _id = ?", new String[]{"20"});
     *
     * List<Note> list2 = noteDao.queryBuilder()
     *                                 .where(NoteDao.Properties.Id.ge(10))
     *                                 .limit(10000)
     *                                 .offset(0)
     *                                 .orderAsc(NoteDao.Properties.Date)
     *                                 .list();
     *
     * QueryBuilder<Note> qb = noteDao.queryBuilder();
     * qb.where(qb.and(NoteDao.Properties.Id.between(10, 15), NoteDao.Properties.Comment.eq("comment"))).list();
     *
     *
     * queryRaw基本上就是对Android原生的查询方法的简单封装，跟踪queryRaw查看其具体的实现，代码如下：
     *
     *      public List<T> queryRaw(String where, String... selectionArg) {
     *              Cursor cursor = db.rawQuery(statements.getSelectAll() + where, selectionArg);
     *              return loadAllAndCloseCursor(cursor);
     *      }
     *
     * 可以看到queryRaw就是将在原生的queryRaw的查询语句前加了"SELECT * FROM Note "，因此只需要再传入具体的查询条件即可。
     * queryBuilder方法采用build链式结构可以灵活地添加各种查询相关的约束，where包含具体的查询条件，limit表示查询数据的条目数量，offset表示查询数据的起始位置，orderAsc表示根据某一列进行排序，最后list得到查询结果。
     * greenDAO还提供了多重条件查询。db.and表示查询条件取"与"，db.or表示查询条件取"或"。
     *
     */

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_greendao2);
    }
}
