package com.jimmysun.tangramdemo.preview.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.jimmysun.tangramdemo.R;

public class IPDialog extends Dialog {

    private EditText mIPEditText;

    private View.OnClickListener mOnOKClickListener;

    public IPDialog setOnOKClickListener(View.OnClickListener onOKClickListener) {
        mOnOKClickListener = onOKClickListener;
        return this;
    }

    public IPDialog( Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_ip);
        setCanceledOnTouchOutside(false);
        mIPEditText = findViewById(R.id.et_ip);
        mIPEditText.setText(HttpUtil.getHostIp(getContext()));
        findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtil.setHostIp(getContext(), mIPEditText.getText().toString());
                if (mOnOKClickListener != null) {
                    mOnOKClickListener.onClick(v);
                }
                dismiss();
            }
        });
        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
