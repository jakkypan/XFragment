package com.container.test;

import android.content.Intent;
import android.widget.Toast;

import com.xfragment.RootActivity;
import com.xfragment.RootFragment;


/**
 * Created by panda on 2017/7/24.
 */
public class MainActivity extends RootActivity {
    @Override
    public RootFragment rootFragment() {
        return new MainFragment();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
