package com.github.lnframeworkdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.github.fragmention.SupportActivity;
import com.github.fragmention.SupportFragment;

import java.util.List;

public class LoginActivity extends SupportActivity {
    private static final String INTENT_ANIMATION = "INTENT_ANIMATION";
    private boolean hasAnimation = true;

    public static void startActivity(Activity activity, boolean hasAnimation) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra(INTENT_ANIMATION, hasAnimation);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        if (intent != null) {
            hasAnimation = intent.getBooleanExtra(INTENT_ANIMATION, true);
        }
        if (savedInstanceState == null) {
            loadRootFragment(R.id.vg_contain, RegisterFragment.newInstance());
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
                new AlertDialog.Builder(LoginActivity.this).setItems(charSequences, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getContainId() {
        return R.id.vg_contain;
    }

    public boolean hasAnimation() {
        return hasAnimation;
    }

    @Override
    protected void excuteBackAnimation() {
        if (hasAnimation) {
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
        }
    }
}
