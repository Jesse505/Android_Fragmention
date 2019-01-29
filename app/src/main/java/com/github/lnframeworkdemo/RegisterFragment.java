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
public class RegisterFragment extends SupportFragment implements View.OnClickListener {

    Button btn_addToLogin;
    Button btn_replaceToLogin;

    private boolean hasAnimation = true;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        btn_addToLogin = getView(view, R.id.btn_addToLogin);
        btn_replaceToLogin = getView(view, R.id.btn_replaceToLogin);

        btn_addToLogin.setOnClickListener(this);
        btn_replaceToLogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addToLogin: {
                addFragment(getContainId(), new LoginFragment());
                break;
            }
            case R.id.btn_replaceToLogin: {
                replaceFragment(getContainId(), new LoginFragment());
                break;
            }
            default: {
                break;
            }
        }
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

}
