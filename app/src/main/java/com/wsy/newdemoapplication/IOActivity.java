package com.wsy.newdemoapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.bean.SeriTestBean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;

/**
 * Created by WangSiYe on 2019/1/10.
 */
public class IOActivity extends BaseActivity {

    String applicationDirectory = "/DemoTestFiles";
    int REQUEST_WRITESTORAGE_PERMISSION = 2;
    String lastAction = "";

    @BindView(R.id.btn_writetofile_b)
    Button btn_writetofile_b;
    @BindView(R.id.btn_readfromfile_b)
    Button btn_readfromfile_b;
    @BindView(R.id.tv_read_result_b)
    TextView tv_read_result_b;

    @BindView(R.id.btn_writetofile)
    Button btn_writetofile;
    @BindView(R.id.btn_readfromfile)
    Button btn_readfromfile;
    @BindView(R.id.tv_read_result)
    TextView tv_read_result;

    @BindView(R.id.btn_writetofile_o)
    Button btn_writetofile_o;
    @BindView(R.id.btn_readfromfile_o)
    Button btn_readfromfile_o;
    @BindView(R.id.tv_read_result_o)
    TextView tv_read_result_o;


    @Override
    protected void init() {
        File file = new File(getSDPath() + applicationDirectory);
        if (!file.exists()) {
            file.mkdir();
        }


        btn_writetofile_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(IOActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    writeToFile_b();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITESTORAGE_PERMISSION);
                    }
                }
            }
        });

        btn_readfromfile_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(IOActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    readFromFile_b();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITESTORAGE_PERMISSION);
                    }
                }
            }
        });

        btn_writetofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(IOActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    writeToFile();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITESTORAGE_PERMISSION);
                    }
                }
            }
        });

        btn_readfromfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(IOActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    readFromFile();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITESTORAGE_PERMISSION);
                    }
                }
            }
        });

        btn_writetofile_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(IOActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    writeToFile_o();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITESTORAGE_PERMISSION);
                    }
                }
            }
        });

        btn_readfromfile_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(IOActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    readFromFile_o();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITESTORAGE_PERMISSION);
                    }
                }
            }
        });


    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_io);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @android.support.annotation.NonNull String[] permissions, @android.support.annotation.NonNull int[] grantResults) {

        if (requestCode == REQUEST_WRITESTORAGE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(IOActivity.this, "权限已申请", Toast.LENGTH_SHORT).show();
                writeToFile_b();

            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {//当拒绝 并没有勾选下次不提醒选项时  这里为true 其他均为false
                        Toast.makeText(IOActivity.this, "权限已拒绝", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(IOActivity.this, "权限已拒绝,请手动打开", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //字节流写入文件
    public void writeToFile_b() {
        FileOutputStream fileOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(new File(getSDPath() + applicationDirectory + "/testIO.txt"));
            byteArrayOutputStream = new ByteArrayOutputStream();

            char[] c = {'1', '2', '3'};
            String str1 = "字节测试写入的内容1\n";
            String str2 = "字节测试写入的内容22\n";
            String str3 = "字节测试写入的内容333\n";

            for (int i = 0; i < c.length; i++) {
                byteArrayOutputStream.write(c[i]);
            }
            byteArrayOutputStream.writeTo(fileOutputStream);

            fileOutputStream.write("\n".getBytes());
            fileOutputStream.write(str1.getBytes());
            fileOutputStream.write(str2.getBytes());
            fileOutputStream.write(str3.getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //字节流读取文件
    public void readFromFile_b() {
        File file = new File(getSDPath() + applicationDirectory + "/testIO.txt");
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                int i = 0;
                byte[] bytes = new byte[1024];
                while ((i = fis.read(bytes)) != -1) {
                    //i 就是从文件中读取的字节，读完后返回-1
                    String s = new String(bytes, 0, i);

                    tv_read_result_b.setText(s);
                }
//                while ((i = fis.read()) != -1) {
//                    //i 就是从文件中读取的字节，读完后返回-1
//                    String s = new String(new byte[1], 0, i);
//
//                    tv_read_result.setText(s);
//                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(IOActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
        }

    }

    //字符写入文件
    public void writeToFile() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(getSDPath() + applicationDirectory + "/testIO_zifu.txt"));

            char[] c = {'1', '5', 's', '好'};
            String str1 = "字符测试1b\n";
            String str4 = "字符测试444\n";

            for (int i = 0; i < c.length; i++) {

                fileWriter.write(c[i]);
                fileWriter.write("\n");
            }

            fileWriter.write(str1);
            fileWriter.write(str4);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //字符读取文件
    public void readFromFile() {
        File file = new File(getSDPath() + applicationDirectory + "/testIO_zifu.txt");
        if (file.exists()) {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(file);
                int i = 0;
                char[] buffer = new char[1024];
                while ((i = fileReader.read(buffer)) != -1) {
                    //i 就是从文件中读取的字节，读完后返回-1
                    String s = new String(buffer, 0, i);

                    tv_read_result.setText(s);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(IOActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
        }

    }

    //实体对象写入文件
    public void writeToFile_o() {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(getSDPath() + applicationDirectory + "/testIO_object.txt"));
            SeriTestBean seriTestBean1 = new SeriTestBean("wang", "male", 44);
            SeriTestBean seriTestBean2 = new SeriTestBean("wang2", "male", 41);

            SeriTestBean seriTestBean3 = new SeriTestBean("wang3", "female", 34);
            SeriTestBean seriTestBean4 = new SeriTestBean("wang44", "female", 33);

            objectOutputStream = new ObjectOutputStream(fileOutputStream);

//            objectOutputStream.writeObject(seriTestBean1);
//            objectOutputStream.writeObject(seriTestBean2);

            List<SeriTestBean> list = new ArrayList<>();
            list.add(seriTestBean3);
            list.add(seriTestBean4);

            objectOutputStream.writeObject(list);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutputStream.flush();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //实体对象读取文件
    public void readFromFile_o() {


        File file = new File(getSDPath() + applicationDirectory + "/testIO_object.txt");
        if (file.exists()) {
            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                List<SeriTestBean> list = new ArrayList<>();
                list = (List<SeriTestBean>) objectInputStream.readObject();

                StringBuffer sb = new StringBuffer();
                Iterator<SeriTestBean> it = list.iterator();
                while (it.hasNext()) {
                    sb.append(it.next().toString());
                }

                tv_read_result_o.setText(sb.toString());


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(IOActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
        }

    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);//判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }
}
