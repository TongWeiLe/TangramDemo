package com.jimmysun.tangramdemo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jimmysun.tangramdemo.preview.util.IPDialog;
import com.jimmysun.tangramdemo.preview.util.PreviewListActivity;
import com.jimmysun.tangramdemo.tangram.TangramActivity;
import com.jimmysun.tangramdemo.virtualview.VirtualViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_tangram).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TangramActivity.class));
            }
        });
        findViewById(R.id.tv_virtual_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VirtualViewActivity.class));
            }
        });

        findViewById(R.id.tv_virtual_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IPDialog dialog = new IPDialog(MainActivity.this);

                dialog.show();


                dialog.setOnOKClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, PreviewListActivity.class));
                    }
                });
            }
        });
    }
}
