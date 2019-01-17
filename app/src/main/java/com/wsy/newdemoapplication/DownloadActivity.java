package com.wsy.newdemoapplication;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.util.OkhttpDownloadUtil;

import java.io.File;
import java.io.RandomAccessFile;

import butterknife.BindView;

/**
 * Created by WangSiYe on 2019/1/17.
 */
public class DownloadActivity extends BaseActivity {

    @BindView(R.id.btn_start)
    Button btn_start;
    @BindView(R.id.btn_okhttp_download)
    Button btn_okhttp_download;


    private String sourceUrl = "http://dldir1.qq.com/dlomg/weishi/weishi_guanwang.apk";
    private DownloadManager manager;
    private long downloadId;

    ProgressDialog progressDialog;


    @Override
    protected void init() {

//        RandomAccessFile 在这种文件类型中可以在任意位置进行write和read

        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        btn_start.setOnClickListener(v -> {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadId);
            query.setFilterByStatus(DownloadManager.STATUS_RUNNING);//正在下载
            Cursor c = manager.query(query);
            if (c.moveToNext()) {
                //正在下载中，不重新下载
            } else {
                //创建下载请求
                DownloadManager.Request down = new DownloadManager.Request(Uri.parse(sourceUrl));
                //设置允许使用的网络类型，这里是移动网络和wifi都可以
                down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                //显示在下载界面，即下载后的文件在系统下载管理里显示
                down.setVisibleInDownloadsUi(true);
                //设置下载标题
                down.setTitle("测试下载apk");
                //显示Notification
//                down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                //设置下载后文件存放的位置，在SDCard/Android/data/你的应用的包名/files/目录下面
//                down.setDestinationInExternalFilesDir(this, null,DOWNLOAD_FILE_NAME);
                down.setDestinationInExternalPublicDir(Constant.TestPath, "/testdownload/test.apk");
                //将下载请求放入队列,返回值为downloadId
                downloadId = manager.enqueue(down);
            }
        });

        btn_okhttp_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(DownloadActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle("正在下载");
                progressDialog.setMessage("请稍后...");
                progressDialog.setProgress(0);
                progressDialog.setMax(100);
                progressDialog.show();
                progressDialog.setCancelable(false);
                OkhttpDownloadUtil.get().download(sourceUrl, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Constant.TestPath + File.separator + "testdownload", "okhttp_download.apk", new OkhttpDownloadUtil.OnDownloadListener() {
                    @Override
                    public void onDownloadSuccess(File file) {
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        //下载完成进行相关逻辑操作  okhttp 回调还是在子线程 需要手动切换
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(DownloadActivity.this, "complete", Toast.LENGTH_SHORT).show();
                            }
                        });

                        Log.e("okhttpdownload","complete");

                    }

                    @Override
                    public void onDownloading(int progress) {
                        progressDialog.setProgress(progress);
                    }

                    @Override
                    public void onDownloadFailed(Exception e) {
                        //下载异常进行相关提示操作
                    }
                });
            }
        });

    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_download);

    }
}
