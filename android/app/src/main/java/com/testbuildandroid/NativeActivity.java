package com.testbuildandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * desc：原生界面
 * author：fox
 * date：2017/12/5
 */
public class NativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        findViewById(R.id.btn_gorn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NativeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_native).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NativeActivity.this,MainActivity.class);
                intent.putExtra("type",1);
                intent.putExtra("message","干得不错");
                startActivity(intent);
            }
        });
    }

}
