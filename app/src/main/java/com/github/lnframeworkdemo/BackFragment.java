package com.github.lnframeworkdemo;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.fragmention.SupportFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class BackFragment extends SupportFragment {


    public BackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_back, container, false);
        Button btn_back = getView(view, R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }

    @Override
    public boolean interceptBackPress() {
        return true;
    }

    @Override
    protected void dealCustomBack() {
        super.dealCustomBack();
        new AlertDialog.Builder(activity).setMessage("这是拦截Fragment的back按键，点击确定返回上一个Fragment").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                popBackStack();
            }
        }).create().show();
    }
}
