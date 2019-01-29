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
 * 日期:17/10/30
 */

public class ThirdFragment extends SupportFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.third_fragment, container, false);
        return view;
    }
}
