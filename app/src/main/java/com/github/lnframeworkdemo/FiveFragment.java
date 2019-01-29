package com.github.lnframeworkdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.fragmention.SupportFragment;

/**
 * 类描述
 * 创建者:tiny
 * 日期:17/12/4
 */

public class FiveFragment extends SupportFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.five_fragment, container, false);
        return view;
    }

    @Override
    public boolean interceptBackPress() {
        return super.interceptBackPress();
    }
}
