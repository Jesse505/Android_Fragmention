package com.github.fragmention;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述
 * 创建者:tiny
 * 日期:17/10/30
 */
public class SupportFragment extends Fragment {
    private static final String TAG = SupportFragment.class.getSimpleName();
    private static final String STATE_SAVE_TAG = "STATE_SAVE_TAG";

    protected Activity activity;
    public String uniqueTag = String.valueOf(System.currentTimeMillis());

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
        check();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            uniqueTag = savedInstanceState.getString(STATE_SAVE_TAG, uniqueTag);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_SAVE_TAG, uniqueTag);
    }

    public <T extends View> T getView(View view, int id) {
        return (T) view.findViewById(id);
    }


    public Fragment addFragment(int containId, SupportFragment fragment) {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            return a.addFragment(containId, fragment);
        } else {
            return fragment;
        }
    }

    public Fragment addFragment(int containId, SupportFragment fragment, boolean hideLast) {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            return a.addFragment(containId, fragment, hideLast);
        } else {
            return fragment;
        }
    }

    protected void loadMultiFragment(int containId, List<? extends SupportFragment> fragments) {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            a.loadMultiFragment(containId, fragments);
        }
    }

    protected Fragment replaceFragment(int containId, SupportFragment fragment) {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            return a.replaceFragment(containId, fragment);
        } else {
            return fragment;
        }
    }

    public Fragment replaceChildFragment(int containId, SupportFragment parent, SupportFragment fragment) {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            return a.replaceChildFragment(containId, parent, fragment);
        } else {
            return fragment;
        }
    }

    public void popBackStack() {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            a.popBackStack();
        }
    }

    public void popBackStackImmediate() {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            a.popBackStackImmediate();
        }
    }

    protected void popBackStack(int count) {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            a.popBackStack(count);
        }
    }

    public SupportFragment findFragmentByTag(String tag) {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            return a.findFragmentByTag(tag);
        } else {
            return null;
        }
    }

    public List<SupportFragment> getFragmentStack() {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            return a.getFragmentStack();
        } else {
            return new ArrayList<>();
        }
    }

    public int getStackSize() {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            return a.getStackSize();
        } else {
            return 0;
        }
    }

    public void backToRoot() {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            a.backToRoot();
        }
    }

    /**
     * fragment入栈动画效果
     *
     * @return 动画配置
     */
    public int[] getAnimationForAdd() {
        return new int[]{R.anim.slide_left_in, R.anim.slide_left_out};
    }

    /**
     * fragment出栈动画效果
     *
     * @return 动画配置
     */
    public int[] getAnimationForPop() {
        return new int[]{R.anim.slide_right_in, R.anim.slide_right_out};
    }

    public boolean interceptBackPress() {
        return false;
    }

    protected int getContainId() {
        if (check()) {
            SupportActivity a = (SupportActivity) activity;
            return a.getContainId();
        } else {
            return 0;
        }
    }

    protected void dealCustomBack() {

    }

    /**
     * 检查activity是否是SupportActivity对象
     *
     * @return true：是，false：不是
     */
    private boolean check() {
        if (!(activity instanceof SupportActivity)) {
            Log.i(TAG, "activity must extends SupportActivity");
            return false;
        }
        return true;
    }
}
