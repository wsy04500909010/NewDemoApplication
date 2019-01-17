package com.wsy.newdemoapplication;

import android.app.DownloadManager;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Button;

import com.wsy.newdemoapplication.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by WangSiYe on 2019/1/17.
 */
public class DownloadActivity extends BaseActivity {

    @BindView(R.id.btn_start)
    Button btn_start;

    private String sourceUrl = "http://dldir1.qq.com/dlomg/weishi/weishi_guanwang.apk";
    private DownloadManager manager ;
    private long downloadId;


    @Override
    protected void init() {

        manager =(DownloadManager)getSystemService(DOWNLOAD_SERVICE);

        btn_start.setOnClickListener(v -> {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadId);
            query.setFilterByStatus(DownloadManager.STATUS_RUNNING);//正在下载
            Cursor c = manager.query(query);
            if(c.moveToNext()){
                //正在下载中，不重新下载
            }else{
                //创建下载请求
                DownloadManager.Request down=new DownloadManager.Request (Uri.parse("http://dldir1.qq.com/dlomg/weishi/weishi_guanwang.apk"));
                //设置允许使用的网络类型，这里是移动网络和wifi都可以
                down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE|DownloadManager.Request.NETWORK_WIFI);
                //显示在下载界面，即下载后的文件在系统下载管理里显示
                down.setVisibleInDownloadsUi(true);
                //设置下载标题
                down.setTitle("测试下载apk");
                //显示Notification
                down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                //设置下载后文件存放的位置，在SDCard/Android/data/你的应用的包名/files/目录下面
//                down.setDestinationInExternalFilesDir(this, null,DOWNLOAD_FILE_NAME);
                down.setDestinationInExternalPublicDir(Constant.TestPath, "/testdownload/test.apk");
                //将下载请求放入队列,返回值为downloadId
                downloadId = manager.enqueue(down);
            }
        });

    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_download);

    }
}
