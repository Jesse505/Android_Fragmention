package com.github.fragmention;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述
 * 创建者:tiny
 * 日期:17/12/2
 */
public class SupportDelegate {
    private FragmentActivity activity;
    private FragmentManager tempFm;
    private FragmentTransaction tempFt;
    private List<SupportFragment> fragmentStack;    //fragment管理栈
    private List<? extends SupportFragment> tempFragments;
    private int tempContainId;

    public SupportDelegate(FragmentActivity activity) {
        this.activity = activity;
        fragmentStack = new ArrayList<>();
        tempFragments = new ArrayList<>();
    }

    public SupportFragment showHideFragment(int containId, SupportFragment showFragment, SupportFragment hideFragment, boolean hasResumed) {
        SupportFragment tempFragment;
        String tag = showFragment.uniqueTag;
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        SupportFragment f = (SupportFragment) fm.findFragmentByTag(tag);
        if (hideFragment != null) {
            ft.hide(hideFragment);
            fragmentStack.remove(hideFragment);
        }
        if (f == null) {
            ft.add(containId, showFragment, tag);
            tempFragment = showFragment;
        } else {
            ft.show(f);
            tempFragment = f;
        }
        fragmentStack.add(tempFragment);
        if (hasResumed) {
            ft.commitNow();
        } else {
            tempFt = ft;
        }
        return tempFragment;
    }

    /**
     * 加载根fragment，不对加入到回退栈里面
     *
     * @param containId  视图id
     * @param fragment   fragment对象
     * @param hasResumed 是否是resume状态
     * @return fragment对象
     */
    public SupportFragment loadRootFragment(int containId, SupportFragment fragment, boolean hasResumed) {
        return dealAddFragment(containId, fragment, false, false, hasResumed, false);
    }

    public SupportFragment addFragment(int containId, SupportFragment fragment, boolean hasResumed) {
        return dealAddFragment(containId, fragment, true, true, hasResumed, true);
    }

    public void loadMultiFragment(int containId, List<? extends SupportFragment> fragments, boolean hasResumed) {
        if (hasResumed) {
            int length = fragments.size();
            for (int i = 0; i < length; i++) {
                SupportFragment fragment = fragments.get(i);
                String tag = fragment.uniqueTag;
                FragmentManager fm = activity.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if (i == 0) {
                    //最底部的不需要加任何动画
                } else if (i == length - 1) {
                    if (fragment.getAnimationForPop() != null) {
                        ft.setCustomAnimations(0, 0,
                                fragment.getAnimationForPop()[0], fragment.getAnimationForPop()[1]);
                        if (fragmentStack.size() >= 1) {
                            ft.hide(fragmentStack.get(fragmentStack.size() - 1));
                        }
                    }
                } else {
                    if (fragment.getAnimationForAdd() != null && fragment.getAnimationForPop() != null) {
                        ft.setCustomAnimations(fragment.getAnimationForAdd()[0], fragment.getAnimationForAdd()[1],
                                fragment.getAnimationForPop()[0], fragment.getAnimationForPop()[1]);
                    }
                    if (fragmentStack.size() >= 1) {
                        ft.hide(fragmentStack.get(fragmentStack.size() - 1));
                    }
                }

                if (i != 0) {
                    ft.addToBackStack(tag);
                }

                ft.add(containId, fragment, tag);

                ft.commit();
                fm.executePendingTransactions();
                fragmentStack.add(fragment);
            }
        } else {
            tempFragments = fragments;
            tempContainId = containId;
        }
    }

    public SupportFragment addFragment(int containId, SupportFragment fragment, boolean hideLast, boolean hasResumed) {
        return dealAddFragment(containId, fragment, true, hideLast, hasResumed, true);
    }

    private SupportFragment dealAddFragment(int containId, SupportFragment fragment, boolean backToStack, boolean hideLast, boolean hasResumed, boolean hasAnimation) {
        String tag = fragment.uniqueTag;
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (hasAnimation) {
            if (fragment.getAnimationForAdd() != null && fragment.getAnimationForPop() != null) {
                ft.setCustomAnimations(fragment.getAnimationForAdd()[0], fragment.getAnimationForAdd()[1],
                        fragment.getAnimationForPop()[0], fragment.getAnimationForPop()[1]);
            }
        }
        ft.add(containId, fragment, tag);
        if (backToStack) {
            if (hideLast) {
                if (fragmentStack.size() >= 1) {
                    ft.hide(fragmentStack.get(fragmentStack.size() - 1));
                }
            }
            ft.addToBackStack(tag);
        }
        if (hasResumed) {
            ft.commit();
        } else {
            tempFt = ft;
        }
        fragmentStack.add(fragment);
        return fragment;
    }

    public SupportFragment replaceFragment(int containId, SupportFragment fragment, boolean hasResumed) {
        String tag = fragment.uniqueTag;
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (fragment.getAnimationForAdd() != null && fragment.getAnimationForPop() != null) {
            ft.setCustomAnimations(fragment.getAnimationForAdd()[0], fragment.getAnimationForAdd()[1],
                    fragment.getAnimationForPop()[0], fragment.getAnimationForPop()[1]);
        }
        ft.replace(containId, fragment, tag);
        ft.addToBackStack(tag);
        if (hasResumed) {
            ft.commit();
        } else {
            tempFt = ft;
        }
        fragmentStack.add(fragment);
        return fragment;
    }

    public SupportFragment replaceChildFragment(int containId, SupportFragment parent, SupportFragment fragment, boolean hasResumed) {
        String tag = fragment.uniqueTag;
        FragmentManager fm = parent.getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containId, fragment, tag);
        if (hasResumed) {
            ft.commit();
        } else {
            tempFt = ft;
        }
        return fragment;
    }

    public void popBackStack() {
        FragmentManager fm = activity.getSupportFragmentManager();
        fm.popBackStack();
        fragmentStack.remove(fragmentStack.size() - 1);
    }

    public void popBackStackImmediate() {
        FragmentManager fm = activity.getSupportFragmentManager();
        fm.popBackStackImmediate();
        fragmentStack.remove(fragmentStack.size() - 1);
    }

    public void popBackStack(int count) {
        for (int i = 0; i < count; i++) {
            popBackStackImmediate();
        }
    }

    public FragmentManager getCurrentFragmentManager() {
        return tempFm;
    }

    public FragmentTransaction getCurrentFragmentTransaction() {
        return tempFt;
    }

    public List<? extends SupportFragment> getCurrentFragments() {
        return tempFragments;
    }

    public int getCurrentContainId() {
        return tempContainId;
    }

    public SupportFragment findFragmentByTag(String tag) {
        FragmentManager fm = activity.getSupportFragmentManager();
        return (SupportFragment) fm.findFragmentByTag(tag);
    }

    public SupportFragment getTopFragment() {
        if (fragmentStack != null && fragmentStack.size() > 0) {
            return fragmentStack.get(fragmentStack.size() - 1);
        }
        return null;
    }

    public int getStackSize() {
        return fragmentStack == null ? 0 : fragmentStack.size();
    }

    public void backToRoot() {
        if (fragmentStack != null && fragmentStack.size() > 1) {
            //两种方式都可以,但下一种在25.4.0之前是有问题的（内存回收）
            while (fragmentStack.size() > 1) {
                popBackStackImmediate();
            }
//            FragmentManager fm = activity.getSupportFragmentManager();
//            fm.popBackStackImmediate(fragmentStack.get(1).getTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            SupportFragment bf = fragmentStack.get(0);
//            fragmentStack.clear();
//            fragmentStack.add(bf);
        }
    }

    public void reset() {
        tempFm = null;
        tempFt = null;
        tempFragments.clear();
        tempContainId = 0;
    }

    public void setFragmentStack(List<SupportFragment> fragments) {
        this.fragmentStack = fragments;
    }

    public List<SupportFragment> getFragmentStack() {
        return fragmentStack;
    }
}
