package com.xfragment;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * fragment跳转的自定义动画
 *
 * Created by panda on 2017/7/24.
 */
public class FragmentAnimBean implements Parcelable {
    public int enter;
    public int exit;
    public int popEnter;
    public int popExit;

    protected FragmentAnimBean(Parcel in) {
        enter = in.readInt();
        exit = in.readInt();
        popEnter = in.readInt();
        popExit = in.readInt();
    }

    public static final Creator<FragmentAnimBean> CREATOR = new Creator<FragmentAnimBean>() {
        @Override
        public FragmentAnimBean createFromParcel(Parcel in) {
            return new FragmentAnimBean(in);
        }

        @Override
        public FragmentAnimBean[] newArray(int size) {
            return new FragmentAnimBean[size];
        }
    };

    public FragmentAnimBean(int enter, int exit, int popEnter, int popExit) {
        this.enter = enter;
        this.exit = exit;
        this.popEnter = popEnter;
        this.popExit = popExit;
    }

    public FragmentAnimBean() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(enter);
        dest.writeInt(exit);
        dest.writeInt(popEnter);
        dest.writeInt(popExit);
    }
}
