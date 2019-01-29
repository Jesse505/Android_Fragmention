package com.github.lnframeworkdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.fragmention.SupportFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Child1Fragment extends SupportFragment {


    public Child1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child1, container, false);
    }

}
