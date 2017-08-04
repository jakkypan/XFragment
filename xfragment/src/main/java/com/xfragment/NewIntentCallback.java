package com.xfragment;

import android.content.Intent;
import android.os.Bundle;

/**
 * 针对single_top和single_task，需要给跳转的fragemnt回调，类似于{@link android.app.Activity#onNewIntent(Intent)}
 *
 * Created by panda on 2017/7/25.
 */
public interface NewIntentCallback {
    void onNewIntent(Bundle bundle);
}
