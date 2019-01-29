package com.github.fragmention;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述
 * 创建者:tiny
 * 日期:17/10/30
 */
public class SupportActivity extends AppCompatActivity {
    private static final String STATE_SAVE_FRAGMENTS = "STATE_SAVE_FRAGMENTS";
    private SupportDelegate supportDelegate = new SupportDelegate(this);
    private boolean isResumed = false;

    private static final String KEY_TRANSACTIONS_POP_BACK_STACK = "pop_back_stack";
    private static final String KEY_TRANSACTIONS_POP_BACK_STACK_IMMEDIATE = "pop_back_stack_immediate";
    private static final String KEY_TRANSACTIONS_POP_BACK_STACK_COUNT = "pop_back_stack_count";
    private static final String KEY_TRANSACTIONS_POP_BACK_TO_ROOT = "back_to_root";

    private Map<String, Integer> mTransactions = new HashMap<>();

    public <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResumed = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
        if (mTransactions != null) {
            if (mTransactions.containsKey(KEY_TRANSACTIONS_POP_BACK_STACK)) {
                popBackStack();
                mTransactions.remove(KEY_TRANSACTIONS_POP_BACK_STACK);
            }

            if (mTransactions.containsKey(KEY_TRANSACTIONS_POP_BACK_STACK_IMMEDIATE)) {
                popBackStackImmediate();
                mTransactions.remove(KEY_TRANSACTIONS_POP_BACK_STACK_IMMEDIATE);
            }

            if (mTransactions.containsKey(KEY_TRANSACTIONS_POP_BACK_STACK_COUNT)) {
                int count = mTransactions.get(KEY_TRANSACTIONS_POP_BACK_STACK_COUNT);
                popBackStack(count);
                mTransactions.remove(KEY_TRANSACTIONS_POP_BACK_STACK_COUNT);
            }

            if (mTransactions.containsKey(KEY_TRANSACTIONS_POP_BACK_TO_ROOT)) {
                backToRoot();
                mTransactions.remove(KEY_TRANSACTIONS_POP_BACK_TO_ROOT);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存fragments集合的标签
        ArrayList<String> tags = new ArrayList<>();
        List<SupportFragment> fragments = supportDelegate.getFragmentStack();
        if (fragments != null) {
            for (SupportFragment supportFragment : fragments) {
                tags.add(supportFragment.uniqueTag);
            }
        }
        outState.putStringArrayList(STATE_SAVE_FRAGMENTS, tags);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //恢复fragments集合
        List<SupportFragment> fragments = new ArrayList<>();
        if (savedInstanceState != null) {
            ArrayList<String> tags = savedInstanceState.getStringArrayList(STATE_SAVE_FRAGMENTS);
            if (tags != null) {
                for (String tag : tags) {
                    SupportFragment bf = findFragmentByTag(tag);
                    if (bf != null) {
                        fragments.add(bf);
                    }
                }
            }
        }
        supportDelegate.setFragmentStack(fragments);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        FragmentManager tempFm = supportDelegate.getCurrentFragmentManager();
        FragmentTransaction tempFt = supportDelegate.getCurrentFragmentTransaction();
        List<? extends SupportFragment> tempFragments = supportDelegate.getCurrentFragments();
        int tempContainId = supportDelegate.getCurrentContainId();
        if (tempFt != null) {
            tempFt.commit();
        }
        if (tempFm != null) {
            tempFm.executePendingTransactions();
        }
        if (tempFragments != null && tempFragments.size() > 0 && tempContainId != 0) {
            supportDelegate.loadMultiFragment(tempContainId, tempFragments, true);
        }
        supportDelegate.reset();
    }

    /**
     * 是否在resume状态
     *
     * @return true, false
     */
    protected boolean hasResumed() {
        return isResumed;
    }

    protected SupportFragment showHideFragment(int containId, SupportFragment showFragment, SupportFragment hideFragment) {
        return supportDelegate.showHideFragment(containId, showFragment, hideFragment, hasResumed());
    }

    protected SupportFragment loadRootFragment(int containId, SupportFragment fragment) {
        return supportDelegate.loadRootFragment(containId, fragment, hasResumed());
    }

    protected SupportFragment addFragment(int containId, SupportFragment fragment) {
        return supportDelegate.addFragment(containId, fragment, hasResumed());
    }

    protected SupportFragment addFragment(int containId, SupportFragment fragment, boolean hideLast) {
        return supportDelegate.addFragment(containId, fragment, hideLast, hasResumed());
    }

    protected void loadMultiFragment(int containId, List<? extends SupportFragment> fragments) {
        supportDelegate.loadMultiFragment(containId, fragments, hasResumed());
    }

    protected SupportFragment replaceFragment(int containId, SupportFragment fragment) {
        return supportDelegate.replaceFragment(containId, fragment, hasResumed());
    }

    protected SupportFragment replaceChildFragment(int containId, SupportFragment parent, SupportFragment childFragment) {
        return supportDelegate.replaceChildFragment(containId, parent, childFragment, hasResumed());
    }

    public void popBackStack() {
        if (isResumed) {
            supportDelegate.popBackStack();
        } else if (mTransactions != null) {
            mTransactions.put(KEY_TRANSACTIONS_POP_BACK_STACK, 0);
        }
    }

    public void popBackStackImmediate() {
        if (isResumed) {
            supportDelegate.popBackStackImmediate();
        } else if (mTransactions != null) {
            mTransactions.put(KEY_TRANSACTIONS_POP_BACK_STACK_IMMEDIATE, 0);
        }
    }

    public void popBackStack(int count) {
        if (isResumed) {
            supportDelegate.popBackStack(count);
        } else if (mTransactions != null) {
            mTransactions.put(KEY_TRANSACTIONS_POP_BACK_STACK_COUNT, count);
        }
    }

    public SupportFragment findFragmentByTag(String tag) {
        return supportDelegate.findFragmentByTag(tag);
    }

    public List<SupportFragment> getFragmentStack() {
        return supportDelegate.getFragmentStack();
    }

    public int getStackSize() {
        return supportDelegate.getStackSize();
    }

    public void backToRoot() {
        if (isResumed) {
            supportDelegate.backToRoot();
        } else if (mTransactions != null) {
            mTransactions.put(KEY_TRANSACTIONS_POP_BACK_TO_ROOT, 0);
        }
    }

    protected boolean interceptBackPress() {
        return false;
    }

    protected void dealCustomBack() {

    }

    @Override
    public final void onBackPressed() {
        List<SupportFragment> fragmentStack = supportDelegate.getFragmentStack();
        if (fragmentStack != null && fragmentStack.size() >= 1) {
            if (fragmentStack.size() > 1) {
                //当前Fragment还不是根Fragment的话，返回事件由Fragment处理
                SupportFragment supportFragment = fragmentStack.get(fragmentStack.size() - 1);
                if (!supportFragment.interceptBackPress()) {
                    supportDelegate.popBackStack();
                } else {
                    supportFragment.dealCustomBack();
                }
            } else {
                //此时处于根Fragment
                SupportFragment supportFragment = fragmentStack.get(0);
                //若根Fragment拦截了返回事件，则交由根Fragment去自行处理
                if (supportFragment.interceptBackPress()) {
                    supportFragment.dealCustomBack();
                } else {
                    //若根Fragment未拦截返回事件，再交由Activity去处理
                    if (interceptBackPress()) {
                        dealCustomBack();
                    } else {
                        super.onBackPressed();
                        excuteBackAnimation();
                    }
                }
            }
        } else {
            //如果栈中没数据了，直接交由Activity退出
            if (interceptBackPress()) {
                dealCustomBack();
            } else {
                super.onBackPressed();
                excuteBackAnimation();
            }
        }
    }

    protected int getContainId() {
        return 0;
    }

    protected void excuteBackAnimation() {

    }
}
