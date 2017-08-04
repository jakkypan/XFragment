package com.container.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.container.R;
import com.container.test.other.SecondActivity;
import com.xfragment.FragmentAnimBean;
import com.xfragment.RootFragment;
import com.xfragment.StackModeManager;

/**
 * Created by panda on 2017/7/24.
 */
public class FourFragemnt extends RootFragment {
    Bundle bundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getBundle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = new LinearLayout(container.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(container.getContext());
        if (bundle == null) {
            textView.setText("i am four");
        } else {
            textView.setText("bundle：" + bundle.toString());
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("test", "test222");
                addFragment(FourFragemnt.this, new SecondFrament(), bundle, null, StackModeManager.SINGLE_TASK);
            }
        });
        layout.addView(textView);

        Button button2 = new Button(container.getContext());
        button2.setText("GO Activity");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentAnimBean animBean = new FragmentAnimBean();
                animBean.enter = R.anim.in;
                animBean.exit = R.anim.out;
                animBean.popEnter = R.anim.fadein;
                animBean.popExit = R.anim.fadeout;
                gotoActivity(SecondActivity.class, null, animBean);
            }
        });
        layout.addView(button2);
        return layout;
    }
}
