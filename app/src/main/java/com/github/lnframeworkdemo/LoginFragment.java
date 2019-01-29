package com.github.lnframeworkdemo;

import android.content.Context;
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
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends SupportFragment implements View.OnClickListener {

    private boolean isNeedWithoutAnimation = false;
    Button btn_addToForgetLoginPwd;
    Button btn_replaceToForgetLoginPwd;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        btn_addToForgetLoginPwd = getView(view, R.id.btn_addToForgetLoginPwd);
        btn_replaceToForgetLoginPwd = getView(view, R.id.btn_replaceToForgetLoginPwd);
        btn_addToForgetLoginPwd.setOnClickListener(this);
        btn_replaceToForgetLoginPwd.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//        if (enter || !isNeedWithoutAnimation) {
//        return super.onCreateAnimation(transit, enter, nextAnim);
//        } else {
//            Animation a = new Animation() {
//            };
//            a.setDuration(0);
//            return a;
//        }

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addToForgetLoginPwd: {
                // isNeedWithoutAnimation = true;
                // popBackStackImmediate();
                addFragment(getContainId(), new ForgetLoginPwdFragment());
                break;
            }
            case R.id.btn_replaceToForgetLoginPwd: {
                replaceFragment(getContainId(), new ForgetLoginPwdFragment());
                break;
            }
            default: {
                break;
            }
        }
    }
}
