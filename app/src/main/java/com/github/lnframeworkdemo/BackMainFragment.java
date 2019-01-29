package com.github.lnframeworkdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.fragmention.SupportFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class BackMainFragment extends SupportFragment {


    public BackMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_back_main, container, false);
        Button btn_toIntercept = getView(view, R.id.btn_toIntercept);
        Button btn_back = getView(view, R.id.btn_back);
        btn_toIntercept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(R.id.vg_contain, new BackFragment());
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }

}
