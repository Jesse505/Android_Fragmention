package com.github.lnframeworkdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.github.fragmention.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetLoginPwdFragment extends SupportFragment {


    public ForgetLoginPwdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_login_pwd, container, false);
        Button btn_backToRoot = getView(view, R.id.btn_backToRoot);
        btn_backToRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToRoot();
            }
        });
        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        LoginActivity ac = (LoginActivity) activity;
        if (ac.hasAnimation()) {
            return super.onCreateAnimation(transit, enter, nextAnim);
        } else {
            Animation a = new Animation() {
            };
            a.setDuration(0);
            return a;
        }
    }

    @Override
    public boolean interceptBackPress() {
        return true;
    }

    @Override
    protected void dealCustomBack() {
        super.dealCustomBack();
        popBackStack();
    }
}
