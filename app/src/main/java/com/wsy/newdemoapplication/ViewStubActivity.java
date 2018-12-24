package com.wsy.newdemoapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wsy.newdemoapplication.bean.ParcelableBean;


/**
 * Created by WSY on 2018/10/18.
 */

public class ViewStubActivity extends AppCompatActivity {

    Button btn_change;

    ViewStub viewStub_text, viewStub_image;
    boolean first = false;
    boolean visi = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstub);

        viewStub_text = (ViewStub) findViewById(R.id.viewstub_text);
        viewStub_image = (ViewStub) findViewById(R.id.viewstub_image);
        btn_change = (Button) findViewById(R.id.btn_change);

        viewStub_text.inflate();
        TextView textView = (TextView) findViewById(R.id.text);

        Bundle bundle = getIntent().getExtras();
        ParcelableBean parcelablebean = bundle.getParcelable("parcebleBean");
        textView.setText(parcelablebean.getName() + "--" + parcelablebean.getAge() + "");

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (first) {
                    if (visi) {
                        visi = false;
                        viewStub_image.setVisibility(View.GONE);
                    } else {
                        visi = true;
                        viewStub_image.setVisibility(View.VISIBLE);
                    }

                } else {
                    viewStub_image.inflate();
                    first = true;
                    visi = true;
                    Toast.makeText(ViewStubActivity.this, "inflate", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
