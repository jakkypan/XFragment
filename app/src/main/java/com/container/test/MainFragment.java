package com.container.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.container.R;
import com.xfragment.FragmentAnimBean;
import com.xfragment.RootFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by panda on 2017/7/24.
 */
public class MainFragment extends RootFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final EditText textView = new EditText(container.getContext());
//        InputFilter filter = new InputFilter() {
//            @Override
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                String speChat="[123]";
//                Pattern pattern = Pattern.compile(speChat);
//                Matcher matcher = pattern.matcher(source.toString());
//                if(matcher.find())return "";
//                else return null;
//            }
//        };
//        textView.setFilters(new InputFilter[]{filter});
//        textView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Log.e("111", "");
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.e("111", "");
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                Log.e("111", "");
//            }
//        });
        textView.setText("Click me");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("test", "test111");

                FragmentAnimBean animBean = new FragmentAnimBean();
                animBean.enter = R.anim.in;
                animBean.exit = R.anim.out;
                animBean.popEnter = R.anim.fadein;
                animBean.popExit = R.anim.fadeout;
                addFragmentForResult(MainFragment.this, new SecondFrament(), bundle, animBean, 100);
            }
        });
        return textView;
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Intent intent) {
        super.onFragmentResult(requestCode, resultCode, intent);
        Toast.makeText(getActivity(), "back from second", Toast.LENGTH_SHORT).show();
    }
}
