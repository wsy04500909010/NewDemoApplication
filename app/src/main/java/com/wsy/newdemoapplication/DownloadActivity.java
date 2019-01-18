package com.wsy.newdemoapplication;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.bean.DownloadHelpBean;
import com.wsy.newdemoapplication.runnable.DownloadRunnable;
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

    @BindView(R.id.btn_start_download)
    Button btn_start_download;
    @BindView(R.id.btn_pause_download)
    Button btn_pause_download;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.tv_jindu)
    TextView tv_jindu;

    private DownloadHelpBean info;//任务信息
    private DownloadRunnable runnable;//下载任务


    private String sourceUrl = "http://dldir1.qq.com/dlomg/weishi/weishi_guanwang.apk";
    private DownloadManager manager;
    private long downloadId;

    ProgressDialog progressDialog;

    //用于更新进度的Handler
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //使用Handler制造一个200毫秒为周期的循环
            handler.sendEmptyMessageDelayed(1, 200);
            //计算下载进度
            int l = (int) ((float) info.getCompletedLen() / (float) info.getContentLen() * 100);
            //设置进度条进度
            progressbar.setProgress(l);

            tv_jindu.setText(l + "%");

            if (l >= 100) {//当进度>=100时，取消Handler循环
                handler.removeCallbacksAndMessages(null);
            }
            return true;
        }
    });


    @Override
    protected void init() {

//        RandomAccessFile 在这种文件类型中可以在任意位置进行write和read

        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);


        //实例化任务信息对象
        info = new DownloadHelpBean("duandian.apk"
                , Environment.getExternalStorageDirectory().getAbsolutePath()
                + Constant.TestPath + "/duandian_Download/"
                , sourceUrl);
        //设置进度条的最大值
        progressbar.setMax(100);


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

                        Log.e("okhttpdownload", "complete");

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

        btn_start_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建下载任务
                runnable = new DownloadRunnable(info);
                //开始下载任务
                new Thread(runnable).start();
                //开始Handler循环
                handler.sendEmptyMessageDelayed(1, 200);

            }
        });

        btn_pause_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用DownloadRunnable中的stop方法，停止下载
                runnable.stop();
                runnable = null;//强迫症，不用的对象手动置空
            }
        });

    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_download);

    }

    @Override
    protected void onDestroy() {
        //在Activity销毁时移除回调和msg，并置空，防止内存泄露
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        super.onDestroy();
    }

}
