package com.github.lnframeworkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.fragmention.SupportFragment;

/**
 * 类描述
 * 创建者:tiny
 * 日期:17/10/30
 */

public class FirstFragment extends SupportFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.first_fragment, container, false);
        Button btn_toLogin = getView(view, R.id.btn_toLogin);
        Button btn_toLoginWithoutAnimation = getView(view, R.id.btn_toLoginWithoutAnimation);
        Button btn_replaceChild = getView(view, R.id.btn_replaceChild);
        Button btn_interceptBack = getView(view, R.id.btn_interceptBack);
        btn_toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.startActivity(activity, true);
                getActivity().overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
            }
        });
        btn_toLoginWithoutAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.startActivity(activity, false);
                getActivity().overridePendingTransition(0, 0);
            }
        });
        btn_replaceChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ReplaceChildActivity.class);
                startActivity(intent);
            }
        });
        btn_interceptBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, InterceptBackActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean interceptBackPress() {
        return super.interceptBackPress();
    }
}
