package com.xfragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 单个activity的fragment的堆栈，为了在Activity被销毁时能自动重建
 *
 * Created by panda on 2017/7/27.
 */
@Deprecated
public class FragmentStack {
    private static FragmentStack instance;
    private Map<String, ArrayList<RootFragment>> stacks = new HashMap<>();

    private FragmentStack() {

    }

    public static FragmentStack getInstance() {
        if (instance == null) {
            instance = new FragmentStack();
        }
        return instance;
    }

    public void put(String activity, RootFragment rootFragment) {
        ArrayList<RootFragment> fragments = new ArrayList<>();
        if (stacks.containsKey(activity)) {
            fragments = stacks.get(activity);
            stacks.remove(activity);
        }
        fragments.add(rootFragment);
        stacks.put(activity, fragments);
    }

    public void pop(String activity) {
        if (stacks.containsKey(activity)) {
            ArrayList<RootFragment> fragments = stacks.get(activity);
            fragments.remove(fragments.size() - 1);
            stacks.remove(activity);
            stacks.put(activity, fragments);
        }
    }

    public ArrayList<RootFragment> getStacks(String activity) {
        ArrayList<RootFragment> fragments = stacks.get(activity);
        stacks.remove(activity);
        return fragments;
    }
}
