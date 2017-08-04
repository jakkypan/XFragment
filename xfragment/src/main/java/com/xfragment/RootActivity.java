package com.xfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 根activity
 *
 * Created by panda on 2017/7/24.
 */
public abstract class RootActivity extends AppCompatActivity {
    public OpsManager stackManager;
    public static final String ANIM_KEY = "bundle_anim_data";
    public static final String NEW_FRAGMENT_KEY = "bundle_new_fragment";
    private FragmentAnimBean mAnimBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = contentView();
        FrameLayout fragmentView = fragmentContainer();
        if (rootView == null) {
            rootView = fragmentView;
        }
        setContentView(rootView);

        stackManager = new OpsManager(this);

        /**
         * TODO 针对activity被销毁的这种处理方式还是有些问题，暂时不使用（特别是在涉及到栈的特性时）。不过这种处理方式是可以解决问题的
         */
//        if (savedInstanceState != null) {
//            RootFragment preFragment = null;
//            ArrayList<RootFragment> rootFragments = FragmentStack.getInstance().getStacks(getClass().getName());
//            for (int i = 0; i < rootFragments.size(); i++) {
//                RootFragment curFragment = rootFragments.get(i);
//                if (i == 0) {
//                    stackManager.replace(curFragment);
//                } else {
//                    stackManager.jumpFragmentForResult(preFragment, curFragment, curFragment.getBundle(), curFragment.getFragmentAnimBean(),
//                            curFragment.getRequestCode(), StackModeManager.STANDARD);
//                }
//
//                preFragment = curFragment;
//            }
//            return;
//        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // 控制是否需要加载基本的base fragment，默认是需要加载的
            // 在从其他activity跳转到另一个activity的fragment时可能不需要
            Class<? extends RootFragment> newFragment = (Class<? extends RootFragment>) bundle.getSerializable(NEW_FRAGMENT_KEY);
            if (newFragment == null) {
                RootFragment fragment = rootFragment();
                if (fragment != null) {
                    stackManager.replace(fragment);
                }
            } else {
                try {
                    RootFragment newRootFragment = newFragment.newInstance();
                    stackManager.replace(newRootFragment, bundle);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            mAnimBean = bundle.getParcelable(ANIM_KEY);
        } else {
            RootFragment fragment = rootFragment();
            if (fragment != null) {
                stackManager.replace(fragment);
            }
        }
    }

    /**
     * 对于需要更多定制化view的，可以实现这个方法。对于不需要定义的可不实现
     *
     * @return
     */
    public ViewGroup contentView() {
        return null;
    }

    /**
     * 内部fragment的的容器
     *
     * @return
     */
    private FrameLayout fragmentContainer() {
        FrameLayout rootLayout = new FrameLayout(this);
        rootLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rootLayout.setId(Resource.getId(this, "xFrameLayoutId"));
        return rootLayout;
    }

    /**
     * activity对应的根部fragment
     *
     * @return
     */
    public abstract RootFragment rootFragment();

    /**
     * 当前附着在activity上的顶层fragment
     *
     * @return
     */
    public RootFragment getCurAttachedFragment() {
        int fragmentsCount = getSupportFragmentManager().getBackStackEntryCount();
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        List<Fragment> realFragments = new ArrayList<>();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                realFragments.add(fragment);
            }
        }
        return (RootFragment) realFragments.get(fragmentsCount);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
            return;
        }

        getCurAttachedFragment().setFragmentResult(0, null);
        popPage();
    }

    /**
     * 界面弹出
     */
    public void popPage() {
        if (isMainThread()) {
            this.getSupportFragmentManager().popBackStackImmediate();
//            FragmentStack.getInstance().pop(getClass().getName());
            // 通知前一个页面，它被展示了
            getCurAttachedFragment().onReShowResume();
        } else {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager().popBackStackImmediate();
//                    FragmentStack.getInstance().pop(getClass().getName());
                    // 通知前一个页面，它被展示了
                    getCurAttachedFragment().onReShowResume();
                }
            });
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (mAnimBean != null) {
            overridePendingTransition(mAnimBean.enter, mAnimBean.exit);
        }
    }

    /**
     * 是否在住线程
     *
     * @return
     */
    private boolean isMainThread() {
        return Thread.currentThread() == this.getMainLooper().getThread();
    }
}