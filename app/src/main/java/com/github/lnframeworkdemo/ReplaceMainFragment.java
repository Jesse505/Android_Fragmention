package com.github.lnframeworkdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.fragmention.SupportFragment;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReplaceMainFragment extends SupportFragment {

    Timer timer;
    int count = 2;

    public ReplaceMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_replace_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            replaceChildFragment(R.id.vg_contain_child, ReplaceMainFragment.this,
                    new Child1Fragment());
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (count % 3 == 1) {
                    replaceChildFragment(R.id.vg_contain_child, ReplaceMainFragment.this, new Child1Fragment());
                } else if (count % 3 == 2) {
                    replaceChildFragment(R.id.vg_contain_child, ReplaceMainFragment.this, new Child2Fragment());
                } else {
                    replaceChildFragment(R.id.vg_contain_child, ReplaceMainFragment.this, new Child3Fragment());
                }
                count++;
            }
        }, 2000, 2000);
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}
