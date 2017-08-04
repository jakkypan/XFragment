package com.xfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * 根fragment
 *
 * Created by panda on 2017/7/24.
 */
public abstract class RootFragment extends Fragment implements NewIntentCallback, FragmentLifecycleDelegate {
    /**
     * 请求码
     */
    private int requestCode;
    /**
     * 回调事件
     */
    private OnFragmentFinishListener mFragmentFinishListener;

    /**
     * 记录fragemnt的动画
     */
    private FragmentAnimBean mFragmentAnimBean;

    /**
     * 获取传入的Bundle数据，对应fragment跳转时的{@link Fragment#setArguments(Bundle)}
     *
     * @return
     */
    public final Bundle getBundle() {
        return getArguments();
    }

    public final void addFragment(RootFragment target, Bundle bundle, FragmentAnimBean animBean) {
        addFragment(null, target, bundle, animBean, StackModeManager.STANDARD);
    }

    public final void addFragment(RootFragment target, Bundle bundle, FragmentAnimBean animBean, @StackModeManager.StackMode int stackMode) {
        addFragment(null, target, bundle, animBean, stackMode);
    }

    public final void addFragment(RootFragment from, RootFragment target, Bundle bundle) {
        addFragment(from, target, bundle, null, StackModeManager.STANDARD);
    }

    public final void addFragment(RootFragment from, RootFragment target, Bundle bundle, @StackModeManager.StackMode int stackMode) {
        addFragment(from, target, bundle, null, stackMode);
    }

    public final void addFragment(RootFragment from, RootFragment target, Bundle bundle, FragmentAnimBean animBean) {
        addFragment(from, target, bundle, animBean, StackModeManager.STANDARD);
    }

    public final void addFragment(RootFragment from, RootFragment target, Bundle bundle, FragmentAnimBean animBean, @StackModeManager.StackMode int stackMode) {
        getRoot().stackManager.jumpFragment(from, target, bundle, animBean, stackMode);
        this.mFragmentAnimBean = animBean;
    }

    public final void addFragmentForResult(RootFragment target, Bundle bundle, FragmentAnimBean animBean, int requestCode) {
        addFragmentForResult(null, target, bundle, animBean, requestCode, StackModeManager.STANDARD);
    }

    public final void addFragmentForResult(RootFragment target, Bundle bundle, FragmentAnimBean animBean, int requestCode, @StackModeManager.StackMode int stackMode) {
        addFragmentForResult(null, target, bundle, animBean, requestCode, stackMode);
    }

    public final void addFragmentForResult(RootFragment from, RootFragment target, Bundle bundle, int requestCode) {
        addFragmentForResult(from, target, bundle, null, requestCode, StackModeManager.STANDARD);
    }

    public final void addFragmentForResult(RootFragment from, RootFragment target, Bundle bundle, int requestCode, @StackModeManager.StackMode int stackMode) {
        addFragmentForResult(from, target, bundle, null, requestCode, stackMode);
    }

    public final void addFragmentForResult(RootFragment from, RootFragment target, Bundle bundle, FragmentAnimBean animBean, int requestCode) {
        addFragmentForResult(from, target, bundle, animBean, requestCode, StackModeManager.STANDARD);
    }

    public final void addFragmentForResult(RootFragment from, RootFragment target, Bundle bundle, FragmentAnimBean animBean, int requestCode, @StackModeManager.StackMode int stackMode) {
        getRoot().stackManager.jumpFragmentForResult(from, target, bundle, animBean, requestCode, stackMode);
        this.mFragmentAnimBean = animBean;
    }

    public final void gotoActivity(Class<? extends RootActivity> target) {
        gotoActivity(target, null);
    }

    public final void gotoActivity(Class<? extends RootActivity> target, Bundle bundle) {
        gotoActivity(target, bundle, null);
    }

    public final void gotoActivity(Class<? extends RootActivity> target, Bundle bundle, FragmentAnimBean animBean) {
        getRoot().stackManager.jumpActivity(target,bundle, animBean);
    }

    public final void gotoActivityForResult(Class<? extends RootActivity> target, int requestCode) {
        gotoActivityForResult(target, null, requestCode);
    }

    public final void gotoActivityForResult(Class<? extends RootActivity> target, Bundle bundle, int requestCode) {
        gotoActivityForResult(target, bundle, null, requestCode);
    }

    public final void gotoActivityForResult(Class<? extends RootActivity> target, Bundle bundle, FragmentAnimBean animBean, int requestCode) {
        getRoot().stackManager.jumpActivityForResult(target,bundle, animBean, requestCode);
    }

    public final void gotoActivityForResult(RootFragment from, Class<? extends RootActivity> target, int requestCode) {
        gotoActivityForResult(from, target, null, requestCode);
    }

    public final void gotoActivityForResult(RootFragment from, Class<? extends RootActivity> target, Bundle bundle, int requestCode) {
        gotoActivityForResult(from, target, bundle, null, requestCode);
    }

    public final void gotoActivityForResult(RootFragment from, Class<? extends RootActivity> target, Bundle bundle, FragmentAnimBean animBean, int requestCode) {
        getRoot().stackManager.jumpActivityForResult(from, target,bundle, animBean, requestCode);
    }

    public final void gotoActivityFragment(Class<? extends RootActivity> target, Class<? extends RootFragment> fragment) {
        gotoActivityFragment(target, fragment, null);
    }

    public final void gotoActivityFragment(Class<? extends RootActivity> target, Class<? extends RootFragment> fragment, Bundle bundle) {
        gotoActivityFragment(target, fragment, bundle, null);
    }

    public final void gotoActivityFragment(Class<? extends RootActivity> target, Class<? extends RootFragment> fragment, Bundle bundle, FragmentAnimBean animBean) {
        getRoot().stackManager.jumpActivityFragment(target, fragment, bundle, animBean);
    }

    public final void gotoActivityFragmentForResult(Class<? extends RootActivity> target, Class<? extends RootFragment> fragment, int requestCode) {
        gotoActivityFragmentForResult(target, fragment, null, requestCode);
    }

    public final void gotoActivityFragmentForResult(Class<? extends RootActivity> target, Class<? extends RootFragment> fragment, Bundle bundle, int requestCode) {
        gotoActivityFragmentForResult(target, fragment, bundle, null, requestCode);
    }

    public final void gotoActivityFragmentForResult(Class<? extends RootActivity> target, Class<? extends RootFragment> fragment, Bundle bundle, FragmentAnimBean animBean, int requestCode) {
        getRoot().stackManager.jumpActivityFragmentForResult(target, fragment, bundle, animBean, requestCode);
    }

    public final void gotoActivityFragmentForResult(RootFragment from, Class<? extends RootActivity> target, Class<? extends RootFragment> fragment, int requestCode) {
        gotoActivityFragmentForResult(from, target, fragment, null, requestCode);
    }

    public final void gotoActivityFragmentForResult(RootFragment from, Class<? extends RootActivity> target, Class<? extends RootFragment> fragment, Bundle bundle, int requestCode) {
        gotoActivityFragmentForResult(from, target, fragment, bundle, null, requestCode);
    }

    public final void gotoActivityFragmentForResult(RootFragment from, Class<? extends RootActivity> target, Class<? extends RootFragment> fragment, Bundle bundle, FragmentAnimBean animBean, int requestCode) {
        getRoot().stackManager.jumpActivityFragmentForResult(from, target, fragment, bundle, animBean, requestCode);
    }

    /**
     * 获取fragment的的容器activity
     *
     * @return
     */
    public final RootActivity getRoot() {
        FragmentActivity activity = getActivity();
        if (activity instanceof RootActivity) {
            return (RootActivity) activity;
        } else {
            throw new ClassCastException("this activity must be extends RootActivity");
        }
    }

    /**
     * 设置数据的回调
     *
     * @param resultCode
     * @param intent
     */
    public void setFragmentResult(int resultCode, Intent intent) {
        if (mFragmentFinishListener != null) {
            mFragmentFinishListener.callback(requestCode, resultCode, intent);
        }
    }

    /**
     * 弹出fragment
     */
    public void popStack() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            getRoot().finish();
        } else {
            getFragmentManager().popBackStackImmediate();
//            FragmentStack.getInstance().pop(getRoot().getClass().getName());
            // 通知前一个页面，它被展示了
            getRoot().getCurAttachedFragment().onReShowResume();
        }
    }

    /**
     * Fragment之间跳转回来后的事件监听
     */
    public interface OnFragmentFinishListener {
        void callback(int requestCode, int resultCode, Intent intent);
    }

    /**
     * 子类fragment重写，实现回调的监听
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    public void onFragmentResult(int requestCode, int resultCode, Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onNewIntent(Bundle bundle) {

    }

    @Override
    public void onCoverStop() {

    }

    @Override
    public void onReShowResume() {

    }

    protected int getRequestCode() {
        return requestCode;
    }

    protected void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public void setFragmentFinishListener(OnFragmentFinishListener fragmentFinishListener) {
        this.mFragmentFinishListener = fragmentFinishListener;
    }

    public FragmentAnimBean getFragmentAnimBean() {
        return mFragmentAnimBean;
    }
}
