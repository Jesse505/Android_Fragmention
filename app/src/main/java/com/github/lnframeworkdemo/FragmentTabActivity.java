package com.github.lnframeworkdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.fragmention.SupportActivity;
import com.github.fragmention.SupportFragment;

import java.util.List;

public class FragmentTabActivity extends SupportActivity implements View.OnClickListener {
    public static final String TEMP_FRAGMENT = "temp_fragment";
    private RelativeLayout rl_first;
    private RelativeLayout rl_second;
    private RelativeLayout rl_third;
    private RelativeLayout rl_forth;
    private SupportFragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab);

        initView();
        initLogic();

        if (savedInstanceState != null) {
            String tempTag = savedInstanceState.getString(TEMP_FRAGMENT);
            if (!TextUtils.isEmpty(tempTag)) {
                tempFragment = (SupportFragment) getSupportFragmentManager().findFragmentByTag(tempTag);
            }
        } else {
//            rl_first.performClick();
            tempFragment = loadRootFragment(R.id.fl_content, new FirstFragment());
        }

        ImageView iv_getStacks = getView(R.id.iv_getStacks);
        iv_getStacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SupportFragment> fragments = getFragmentStack();
                int size = fragments.size();
                CharSequence[] charSequences = new CharSequence[size];
                for (int i = 0; i < fragments.size(); i++) {
                    charSequences[size - i - 1] = fragments.get(i).getClass().getSimpleName();
                }
                new AlertDialog.Builder(FragmentTabActivity.this).setItems(charSequences, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
            }
        });
    }

    private void initView() {
        rl_first = getView(R.id.rl_first);
        rl_second = getView(R.id.rl_second);
        rl_third = getView(R.id.rl_third);
        rl_forth = getView(R.id.rl_forth);
    }

    private void initLogic() {
        rl_first.setOnClickListener(this);
        rl_second.setOnClickListener(this);
        rl_third.setOnClickListener(this);
        rl_forth.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (tempFragment != null) {
            outState.putString(TEMP_FRAGMENT, tempFragment.getTag());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_first: {
                showHideFragment(new FirstFragment(), tempFragment);
                break;
            }
            case R.id.rl_second: {
                showHideFragment(new SecondFragment(), tempFragment);
                break;
            }
            case R.id.rl_third: {
                showHideFragment(new ThirdFragment(), tempFragment);
                break;
            }
            case R.id.rl_forth: {
                showHideFragment(new ForthFragment(), tempFragment);
                break;
            }
            default: {
                break;
            }
        }
    }

    protected Fragment showHideFragment(SupportFragment showFragment, SupportFragment hideFragment) {
        tempFragment = (SupportFragment) showHideFragment(R.id.fl_content, showFragment, hideFragment);
        return tempFragment;
    }

    @Override
    protected boolean interceptBackPress() {
        return true;
    }

    @Override
    protected void dealCustomBack() {
        super.dealCustomBack();
        Toast.makeText(this, "拦截back，自定义处理", Toast.LENGTH_SHORT).show();
        finish();
    }
}
