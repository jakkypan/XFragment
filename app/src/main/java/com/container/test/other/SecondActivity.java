package com.container.test.other;

import android.content.Intent;
import android.widget.Toast;

import com.xfragment.RootActivity;
import com.xfragment.RootFragment;

/**
 * Created by panda on 2017/7/25.
 */
public class SecondActivity extends RootActivity {
    @Override
    public RootFragment rootFragment() {
        return new SSecondFragment();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "ok!!!!", Toast.LENGTH_SHORT).show();
    }
}
