package com.wsy.newdemoapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by WangSiYe on 2019/4/4.
 */
public class DownImageActivity extends BaseActivity {

    private static final String TAG = "DownImageActivity";
    private Button mButton;
    ImageView imageView;


    @Override
    protected void init() {
        mButton = findViewById(R.id.btn_downimg);
        imageView = findViewById(R.id.img);

        //小图片
        String imgurl = "http://e.hiphotos.baidu" +
                ".com/image/h%3D300/sign=28f9f6093dfa828bce239be3cd1e41cd" +
                "/0eb30f2442a7d9335c52967fa34bd11372f001c7.jpg";
        //高清测试
        String imgurl2 = "http://attach.bbs.miui.com/forum/201408/29/123548rn4zkusqnbknauad.jpg";

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage(imgurl2);
            }
        });

        displayImg();


    }

    private void displayImg(){

        //本地文件
        File file = new File(getFilesDir(), "testimg.jpg");
        //加载图片
        if (file.exists()){

            Glide.with(this).load(file).into(imageView);
        }
    }

    private void downloadImage(String imgurl) {
        //原生下载
//        new DownLoadTask().execute(imgurl);
        //使用Glide下载
        download(imgurl);

    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_downimg);
    }

    private class DownLoadTask extends AsyncTask<String, Integer, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... strings) {

            return getBitmapByUrl(strings[0]);
        }

        /**
         * 在调用cancel方法后会执行到这里
         */
        protected void onCancelled() {
            Log.i(TAG, "onCancelled");
        }

        /**
         * 在doInbackground之后执行
         */
        protected void onPostExecute(Bitmap args3) {
            Log.i(TAG, "onPostExecute:" + args3);
            saveImage(args3, getFilesDir().getPath(), "testimg.jpg");
        }

        /**
         * 在doInBackground之前执行
         */
        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
        }

        /**
         * 特别赞一下这个多次参数的方法，特别方便
         *
         * @param args2
         */
        @Override
        protected void onProgressUpdate(Integer... args2) {
            Log.i(TAG, "onProgressUpdate:" + args2[0]);
        }
    }


    /**
     * 通过URL地址获取Bitmap对象 * @Title: getBitMapByUrl * @param @param url * @param @return * @param
     * @throws Exception * @return Bitmap * @throws
     */
    public Bitmap getBitmapByUrl(final String url) {
        URL fileUrl = null;
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            fileUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            is = null;
        }
        return bitmap;
    }

    //保存图片到本地路径
    public File saveImage(Bitmap bmp, String path, String fileName) {
        File appDir = new File(path);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    // 保存图片到手机
    public void download(final String url) {

        new AsyncTask<Void, Integer, File>() {

            @Override
            protected File doInBackground(Void... params) {
                File file = null;
                try {
                    FutureTarget<File> future = Glide
                            .with(DownImageActivity.this)
                            .load(url)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);

                    file = future.get();

                    // 首先保存图片
//                    File pictureFolder = Environment.getExternalStoragePublicDirectory
// (Environment.DIRECTORY_PICTURES).getAbsoluteFile();
//
//                    File appDir = new File(pictureFolder ,"Beauty");
//                    if (!appDir.exists()) {
//                        appDir.mkdirs();
//                    }

                    File appDir = getFilesDir();

                    String fileName = "testimg.jpg";
//                    String fileName = System.currentTimeMillis() + ".jpg";
                    File destFile = new File(appDir, fileName);

                    FileUtil.copy(file, destFile);

                    // 最后通知图库更新
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.fromFile(new File(destFile.getPath()))));


                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
                return file;
            }

            @Override
            protected void onPostExecute(File file) {

                displayImg();
                Toast.makeText(DownImageActivity.this, "saved ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }
        }.execute();
    }
}
