package com.github.lnframeworkdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.github.fragmention.SupportActivity;

public class InterceptBackActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercept_back);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.vg_contain, new BackMainFragment());
        }
    }

    @Override
    protected boolean interceptBackPress() {
        return true;
    }

    @Override
    protected void dealCustomBack() {
        super.dealCustomBack();
        new AlertDialog.Builder(this).setMessage("这是拦截Activity的back按键，因为此时已经在根节点了，所以只会走Activity的返回事件，点击确定关闭Activity").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).create().show();
    }
}
