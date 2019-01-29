package com.github.lnframeworkdemo;

import android.os.Bundle;

import com.github.fragmention.SupportActivity;

public class ReplaceChildActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_child);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.vg_contain, new ReplaceMainFragment());
        }
    }
}
